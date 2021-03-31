package com.itavgur.otus.sa.app.web.dto;

import com.itavgur.otus.sa.app.domain.City;
import lombok.Value;

@Value
public class PersonDto {

    Long id;
    String firstName;
    String lastName;
    City city;
    Boolean enabled;


}
