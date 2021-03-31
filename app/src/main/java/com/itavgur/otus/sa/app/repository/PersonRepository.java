package com.itavgur.otus.sa.app.repository;

import com.itavgur.otus.sa.app.domain.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {


}

