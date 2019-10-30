package com.edu.booksearch.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeUtilTest {

    private DateTimeUtil dateTimeUtil = new DateTimeUtil();

    @Test
    void 카카오_시간_변환() {
        String datetime = "2014-11-17T00:00:00.000+09:00";
        System.out.println(">" + dateTimeUtil.kakaoDateParser(datetime));

    }
}