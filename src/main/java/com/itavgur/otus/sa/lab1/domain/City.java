package com.itavgur.otus.sa.lab1.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(schema = "otus_sa", name = "city")
@Data
public class City {

    @Id
    @GeneratedValue(generator = "city_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "city_gen", schema = "otus_sa", initialValue = 1000, allocationSize = 1)
    private Long id;

    private String name;

}
