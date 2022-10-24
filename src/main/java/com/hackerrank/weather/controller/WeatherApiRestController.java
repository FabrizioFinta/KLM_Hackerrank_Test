package com.hackerrank.weather.controller;

import com.google.common.collect.Lists;
import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.protocol.dto.WeatherDTO;
import com.hackerrank.weather.protocol.route.WeatherRoute;
import com.hackerrank.weather.repository.WeatherRepository;
import com.hackerrank.weather.util.DateTimeUtil;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WeatherApiRestController {

    private final WeatherRepository weatherRepository;
    private final ModelMapper mapper;

    @PostMapping(WeatherRoute.POST_WEATHER_ROUTE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<WeatherDTO> postWeather(@RequestBody WeatherDTO weatherDTO){
        if (weatherDTO.getId() != null){
                return ResponseEntity.badRequest().build();
        }
        Weather weather = mapper.map(weatherDTO, Weather.class);
        Weather createdWeather = weatherRepository.save(weather);
        WeatherDTO createdWeatherDTO = mapper.map(createdWeather, WeatherDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(createdWeatherDTO);
    }

    @GetMapping(WeatherRoute.GET_WEATHER_BY_ID)
    public ResponseEntity<WeatherDTO> getWeatherById(@PathVariable Integer id){
        Optional<Weather> optWeather = weatherRepository.findById(id);
        if (!optWeather.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        WeatherDTO result = mapper.map(optWeather.get(), WeatherDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //date, city multi, sort= date or -date
    @GetMapping(WeatherRoute.GET_WEATHERS_ROUTE)
    public ResponseEntity<List<WeatherDTO>> getWeathers(
        @RequestParam(name = "date") Optional<String> dateOpt,
        @RequestParam(name = "city") Optional<List<String>> cityOpt,
        @RequestParam(name = "sort") Optional<String> sortOpt){
        try{
            validate(dateOpt, cityOpt, sortOpt);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }

        List<Weather> result = Lists.newArrayList();
        if (dateOpt.isPresent()) {
            Date dateParameter = DateTimeUtil.dateFromLocalDate(
                DateTimeUtil.stringToLocalDate(dateOpt.get()));
            if (sortOpt.isPresent()) {
                switch (sortOpt.get()) {
                    case "date":
                        result = weatherRepository.findByDateOrderByDate(dateParameter);
                    case "-date":
                        result = weatherRepository.findByDateOrderByDateDesc(dateParameter);
                }
            } else {
                result = weatherRepository.findByDate(dateParameter);
            }
        } else if (cityOpt.isPresent()){
            List<String> cityParameter = cityOpt.get();
            if (sortOpt.isPresent()){
                switch (sortOpt.get()){
                    case "date":
                        result = weatherRepository.findByCityInIgnoreCaseOrderByDate(cityParameter);
                    case "-date":
                        result = weatherRepository.findByCityInIgnoreCaseOrderByDateDesc(cityParameter);
                }
            } else {
                result = weatherRepository.findByCityInIgnoreCase(cityParameter);
            }
        } else {
            if (sortOpt.isPresent()) {
                switch (sortOpt.get()) {
                    case "date":
                        result = weatherRepository.findAllByOrderByDate();
                    case "-date":
                        result = weatherRepository.findAllByOrderByDateDesc();
                }
            } else {
                result = weatherRepository.findAll();
            }
        }

        List<WeatherDTO> resultDTOs = result.stream()
            .map(this::mapToWeatherDTO)
            .collect(Collectors.toList());

        return ResponseEntity.ok(resultDTOs);
    }

    private void validate(Optional<String> dateOpt, Optional<List<String>> cityOpt,
        Optional<String> sortOpt) {

        if (dateOpt.isPresent() && cityOpt.isPresent()){
            throw new IllegalArgumentException();
        }
        if (sortOpt.isPresent()) {
            String sort = sortOpt.get();
            if (sort.equals("date") || sort.equals("-date")) {
                return;
            }
            throw new IllegalArgumentException();
        }
    }

    private WeatherDTO mapToWeatherDTO(Weather weather) {
        return mapper.map(weather, WeatherDTO.class);
    }
}
