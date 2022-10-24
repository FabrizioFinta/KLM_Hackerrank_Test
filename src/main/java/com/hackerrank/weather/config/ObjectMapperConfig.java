package com.hackerrank.weather.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

    @Autowired
    public void configureDefaultTimeZone(ObjectMapper objectMapper){
        objectMapper.setTimeZone(TimeZone.getDefault());
    }
}
