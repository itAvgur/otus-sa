package com.itavgur.otus.sa.lab1.web.dto;

import lombok.Value;

@Value
public class PersonDto {

    Long id;
    String firstName;
    String lastName;
    String city;
    Boolean enabled;


}
