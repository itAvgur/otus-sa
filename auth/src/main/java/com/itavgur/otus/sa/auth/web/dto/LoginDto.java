package com.itavgur.otus.sa.auth.web.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated
public class LoginDto {

    @NotNull
    String login;

    @NotNull
    String password;

}
