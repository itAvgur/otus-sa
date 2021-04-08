package com.itavgur.otus.sa.auth.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(schema = "auth", name = "users")
public class User {

    @Id
    @SequenceGenerator(name = "user_generator", schema = "auth", sequenceName = "user_seq")
    @GeneratedValue(generator = "user_generator", strategy = GenerationType.SEQUENCE)
    Long id;

    @NotNull
    String login;

    @NotNull
    String password;

    @NotNull
    String email;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "birth_date")
    LocalDate birthDate;

    Boolean enabled;

}
