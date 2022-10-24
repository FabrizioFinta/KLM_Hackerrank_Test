package com.hackerrank.weather.protocol.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.Lists;
import com.hackerrank.weather.config.CustomDateTimeFormat;
import com.hackerrank.weather.config.serilaization.CustomListDoubleSerializer;
import com.hackerrank.weather.config.serilaization.CustomFloatSerializer;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class WeatherDTO {

    private Integer id;
    @JsonFormat(shape = Shape.STRING, pattern = CustomDateTimeFormat.GENERAL_DATE_FORMAT_PATTERN)
    private Date date;

    @JsonSerialize(using = CustomFloatSerializer.class)
    private Float lat;
    @JsonSerialize(using = CustomFloatSerializer.class)
    private Float lon;
    private String city;
    private String state;

    @JsonSerialize(using = CustomListDoubleSerializer.class)
    private List<Double> temperatures = Lists.newArrayList();
}
