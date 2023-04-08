package com.noobs.gazonuz.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class Utils {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-ss hh:mm:ss");


    public String formatDate(LocalDateTime localDateTime) {
        return DATE_TIME_FORMATTER.format(localDateTime);
    }
}
