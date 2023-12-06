package com.itavgur.otus.sa.person.web.dto;

import com.itavgur.otus.sa.person.domain.City;
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
