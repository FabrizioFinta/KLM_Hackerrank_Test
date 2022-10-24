package com.hackerrank.weather.repository;

import com.hackerrank.weather.model.Weather;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<Weather, Integer> {
    List<Weather> findAllByOrderByDate();
    List<Weather> findAllByOrderByDateDesc();

    List<Weather> findByDate(Date date);
    List<Weather> findByDateOrderByDate(Date date);
    List<Weather> findByDateOrderByDateDesc(Date date);

    List<Weather> findByCityInIgnoreCase(List<String> city);
    List<Weather> findByCityInIgnoreCaseOrderByDate(List<String> city);
    List<Weather> findByCityInIgnoreCaseOrderByDateDesc(List<String> city);
}
