package com.itavgur.otus.sa.auth.web.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    @NotNull
    String login;

    @NotNull
    String password;

    @NotNull
    String email;

    String firstName;

    String lastName;

    LocalDate birthDate;

}
