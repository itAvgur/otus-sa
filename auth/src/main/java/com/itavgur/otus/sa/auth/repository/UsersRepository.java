package com.itavgur.otus.sa.auth.repository;

import com.itavgur.otus.sa.auth.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<User, Long> {

    Optional<User> findByLoginAndPassword(String login, String password);

}
