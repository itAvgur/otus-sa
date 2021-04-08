package com.itavgur.otus.sa.auth.service;

import com.itavgur.otus.sa.auth.domain.User;
import com.itavgur.otus.sa.auth.repository.UsersRepository;
import com.itavgur.otus.sa.auth.web.dto.LoginDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthService {

    UsersRepository usersRepository;

    public User register(User user) {
        return usersRepository.save(user);
    }

    public Optional<User> authenticate(LoginDto loginDto) {
        return usersRepository.findByLoginAndPassword(loginDto.getLogin(), loginDto.getPassword());
    }
}
