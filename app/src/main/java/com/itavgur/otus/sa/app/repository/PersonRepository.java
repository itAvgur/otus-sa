package com.itavgur.otus.sa.app.repository;

import com.itavgur.otus.sa.app.domain.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {

    Optional<Person> findByLogin(String userLogin);


}

