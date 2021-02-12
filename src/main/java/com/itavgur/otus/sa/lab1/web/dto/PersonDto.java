package com.itavgur.otus.sa.lab1.web.dto;

import com.itavgur.otus.sa.lab1.domain.City;
import lombok.Value;

@Value
public class PersonDto {

    Long id;
    String firstName;
    String lastName;
    City city;
    Boolean enabled;


}
