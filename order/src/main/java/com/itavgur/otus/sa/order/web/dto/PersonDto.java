package com.itavgur.otus.sa.order.web.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PersonDto {

    Long id;
    String login;
}