package com.itavgur.otus.sa.auth.web;

import com.itavgur.otus.sa.auth.domain.User;
import com.itavgur.otus.sa.auth.exception.NotFoundException;
import com.itavgur.otus.sa.auth.service.AuthService;
import com.itavgur.otus.sa.auth.service.SessionService;
import com.itavgur.otus.sa.auth.web.dto.LoginDto;
import com.itavgur.otus.sa.auth.web.dto.UserDto;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.*;
import java.util.Arrays;
import java.util.Optional;

@Validated
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {

    private static final String SESSION_ID_COOKIE = "SESSIONID";

    AuthService authService;
    SessionService sessionService;

    @PostMapping("/register")
    @Validated
    public ResponseEntity<UserDto> register(@RequestBody UserDto user) {

        return new ResponseEntity<>(authService.register(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody @Validated LoginDto loginDto, HttpServletResponse response) {

        User authenticatedUser = authService.authenticate(loginDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, String.format("user with login %s doesn't exist", loginDto.getLogin())));

        if (Boolean.FALSE.equals(authenticatedUser.getEnabled())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "user with login disabled");
        }
        response.addCookie(new Cookie(SESSION_ID_COOKIE, sessionService.create(authenticatedUser)));
        return new ResponseEntity<>(authenticatedUser, HttpStatus.OK);

    }

    @GetMapping("/check")
    public String check(HttpServletRequest httpServletRequest) {

        String sessionId = getSessionId(httpServletRequest);

        return sessionService.findUserLogin(sessionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, String.format("sessionId %s doesn't exist", sessionId)));

    }

    @GetMapping("/logout")
    public void registerUser(HttpServletRequest httpServletRequest) {

        String sessionId = getSessionId(httpServletRequest);

        try {
            sessionService.remove(sessionId);
        } catch (NotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("sessionId %s doesn't exist", sessionId));
        }

    }

    private String getSessionId(HttpServletRequest httpServletRequest) {

        if (httpServletRequest.getCookies() == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "sessionId empty");
        }

        Optional<Cookie> sessionIdCookie = Arrays.stream(httpServletRequest.getCookies()).
                filter(cookie -> SESSION_ID_COOKIE.equals(cookie.getName()))
                .findFirst();

        if (!sessionIdCookie.isPresent() || StringUtils.isBlank(sessionIdCookie.get().getValue())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "sessionId empty");
        }

        return sessionIdCookie.get().getValue();

    }
}
