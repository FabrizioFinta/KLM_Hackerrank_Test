package com.hackerrank.weather.util;

import com.hackerrank.weather.config.CustomDateTimeFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public final class DateTimeUtil {
    public static Date dateFromLocalDate(LocalDate localDate){
        return Date.from(
            localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }


    public static LocalDate stringToLocalDate(String dateAsString){
        return LocalDate.from(
            CustomDateTimeFormat.GENERAL_DATE_TIME_FORMATTER.parse(dateAsString));
    }
}
