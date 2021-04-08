package com.itavgur.otus.sa.app.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(schema = "customers", name = "personal_data")
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    public static final int START_SEQ = 10000;

    @Id
    @GeneratedValue(generator = "person_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "person_seq", schema = "customers", sequenceName = "personal_data_seq", initialValue = START_SEQ, allocationSize = 1)
    private Long id;

    private String login;
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "birth_date")
    private LocalDate birthDate;

}
