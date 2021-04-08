package com.itavgur.otus.sa.app.web.dto;

import com.itavgur.otus.sa.app.domain.City;
import lombok.Value;

import java.time.LocalDate;

@Value
public class PersonDto {

    String login;
    String email;
    String firstName;
    String lastName;
    City city;
    LocalDate birthDate;

}
