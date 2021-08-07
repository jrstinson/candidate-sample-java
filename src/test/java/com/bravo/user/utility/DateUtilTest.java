package com.bravo.user.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DateUtilTest {

    LocalDateTime now;
    DateTimeFormatter dTimeFormatter;

    @BeforeEach
    void setupDateUtil() {
        now = LocalDateTime.now();
    }

    @Test
    void shouldProvideDateOnlyFormat() {
        dTimeFormatter = DateUtil.DATE_FORMAT;
        assertEquals(dTimeFormatter.format(now), String.format("%04d", now.getYear()) + "-"
                + String.format("%02d", now.getMonthValue()) + "-" + String.format("%02d", now.getDayOfMonth()));
    }

    @Test
    void shouldProvideDateTimeFormat() {
        dTimeFormatter = DateUtil.DATE_TIME_FORMAT;
        assertEquals(dTimeFormatter.format(now),
                String.format("%04d", now.getYear()) + "-" + String.format("%02d", now.getMonthValue()) + "-"
                        + String.format("%02d", now.getDayOfMonth()) + " " + String.format("%02d", now.getHour()) + ":"
                        + String.format("%02d", now.getMinute()) + ":" + String.format("%02d", now.getSecond()));
    }

    @Test
    void shouldProvideTimeOnlyFormat() {
        dTimeFormatter = DateUtil.TIME_FORMAT;
        assertEquals(dTimeFormatter.format(now), String.format("%02d", now.getHour()) + ":"
                + String.format("%02d", now.getMinute()) + ":" + String.format("%02d", now.getSecond()));
    }
}