package com.itavgur.otus.sa.lab1.web.dto;


import com.sun.org.apache.regexp.internal.RE;
import lombok.*;

@Value
public class StatusDto {

    private static final String RESPONSE_OK_VALUE = "OK";

    String status;

    public static StatusDto of(String value) {
        return new StatusDto(value);
    }

    public static StatusDto ok() {
        return new StatusDto(RESPONSE_OK_VALUE);
    }

}
