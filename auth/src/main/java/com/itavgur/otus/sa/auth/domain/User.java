package com.itavgur.otus.sa.auth.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(schema = "auth", name = "users")
public class User {

    public static final int START_SEQ = 10000;

    @Id
    @SequenceGenerator(name = "user_generator", schema = "auth", sequenceName = "user_seq", initialValue = START_SEQ, allocationSize = 1)
    @GeneratedValue(generator = "user_generator", strategy = GenerationType.SEQUENCE)
    Long id;

    @NotNull
    String login;

    @NotNull
    String password;

    Boolean enabled;

}
