package com.hackerrank.weather.config;

import java.time.format.DateTimeFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CustomDateTimeFormat {
    public static final String GENERAL_DATE_FORMAT_PATTERN = "yyyy-MM-dd";

    public static final DateTimeFormatter GENERAL_DATE_TIME_FORMATTER =
        DateTimeFormatter.ofPattern(GENERAL_DATE_FORMAT_PATTERN);
}
