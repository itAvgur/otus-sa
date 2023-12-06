package com.itavgur.otus.sa.person.web.dto;

import lombok.Value;

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
