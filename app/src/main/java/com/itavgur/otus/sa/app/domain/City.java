package com.itavgur.otus.sa.app.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(schema = "customers", name = "city")
@Data
public class City {

    @Id
    @GeneratedValue(generator = "city_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "city_gen", schema = "customers", initialValue = 1000, allocationSize = 1)
    private Long id;

    private String name;

}
