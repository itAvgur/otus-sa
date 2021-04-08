package com.itavgur.otus.sa.auth.service;

import com.itavgur.otus.sa.auth.domain.User;
import com.itavgur.otus.sa.auth.repository.UsersRepository;
import com.itavgur.otus.sa.auth.web.dto.LoginDto;
import com.itavgur.otus.sa.auth.web.dto.UserDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthService {

    UsersRepository usersRepository;
    RestTemplate restTemplate;

    @Value("${service.personaldata.create.url}")
    @NonFinal
    String personalDataCreateUrl;

    @Transactional
    public UserDto register(UserDto userDto) {

        restTemplate.postForEntity(personalDataCreateUrl, userDto, Object.class);

        usersRepository.save(getUserFromDto(userDto));
        return userDto;
    }

    public Optional<User> authenticate(LoginDto loginDto) {
        return usersRepository.findByLoginAndPassword(loginDto.getLogin(), loginDto.getPassword());
    }

    private User getUserFromDto(UserDto userDto) {
        User res = new User();
        res.setLogin(userDto.getLogin());
        res.setPassword(userDto.getPassword());
        res.setEnabled(true);
        return res;
    }
}
