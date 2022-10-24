package com.hackerrank.weather.protocol.route;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class WeatherRoute {
    public static final String POST_WEATHER_ROUTE = "/weather";

    public static final String GET_WEATHERS_ROUTE = "/weather";

    public static final String GET_WEATHER_BY_ID = "/weather/{id}";

}
