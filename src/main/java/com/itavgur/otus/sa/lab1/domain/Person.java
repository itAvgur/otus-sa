package com.itavgur.otus.sa.lab1.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(schema = "OTUS_SA", name = "PERSON")
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    public static final int START_SEQ = 100000;

    @Id
    @GeneratedValue(generator = "person_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "person_seq", schema = "OTUS_SA", sequenceName = "person_seq", initialValue = START_SEQ, allocationSize = 1)
    private Long id;

    private String firstName;

    private String lastName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;

    private Boolean enabled;

}
