package com.itavgur.otus.sa.lab1.repository;

import com.itavgur.otus.sa.lab1.domain.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {


}

