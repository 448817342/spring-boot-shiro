package com.mye.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author zb
 * @date 2019-07-22 11:38
 **/
public class TimeUtil {

    private final static String LOCAL_DATE = "yyyy-MM-dd";
    private final static String LOCAL_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    public static LocalDate toLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(LOCAL_DATE));
    }

    public static LocalDateTime toLocalDateTime(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(LOCAL_DATE_TIME));
    }

    public static String localDateToString(LocalDate date) {
        return DateTimeFormatter.ofPattern(LOCAL_DATE).format(date);
    }

    public static String localDateTimeToString(LocalDateTime date) {
        return DateTimeFormatter.ofPattern(LOCAL_DATE_TIME).format(date);
    }

}
