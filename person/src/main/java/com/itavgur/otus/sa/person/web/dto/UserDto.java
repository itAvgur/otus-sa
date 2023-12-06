package com.itavgur.otus.sa.person.web.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    Long id;
    String login;
    String password;
    String email;
    String firstName;
    String lastName;
    LocalDate birthDate;
    Boolean enabled;

}
