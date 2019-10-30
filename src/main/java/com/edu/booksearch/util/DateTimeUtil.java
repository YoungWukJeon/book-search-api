package com.edu.booksearch.util;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    public static LocalDateTime kakaoDateParser(String datetime) {
        return LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.000VV"));
    }
}
