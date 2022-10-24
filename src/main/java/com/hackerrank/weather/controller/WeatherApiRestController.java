package com.hackerrank.weather.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.protocol.dto.WeatherDTO;
import com.hackerrank.weather.protocol.route.WeatherRoute;
import com.hackerrank.weather.repository.WeatherRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
                return ResponseEntity.badRequest().body(null);
        }
        Weather weather = mapper.map(weatherDTO, Weather.class);
        Weather createdWeather = weatherRepository.save(weather);
        WeatherDTO createdWeatherDTO = mapper.map(createdWeather, WeatherDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(createdWeatherDTO);
    }
}
