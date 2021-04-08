package com.itavgur.otus.sa.auth.service.dto;

import lombok.Value;

import java.time.LocalDate;

@Value
public class PersonDto {

    Long id;
    String login;
    String email;
    String firstName;
    String lastName;
    String city;
    LocalDate birthDate;

}
