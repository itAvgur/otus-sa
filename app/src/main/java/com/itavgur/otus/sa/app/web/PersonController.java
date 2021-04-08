package com.itavgur.otus.sa.app.web;

import com.itavgur.otus.sa.app.domain.Person;
import com.itavgur.otus.sa.app.exception.NotFoundException;
import com.itavgur.otus.sa.app.service.AuthService;
import com.itavgur.otus.sa.app.service.PersonService;
import com.itavgur.otus.sa.app.web.dto.PersonDto;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;


@RestController
@RequestMapping("/person")
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    public static final String SESSION_ID_COOKIE = "SESSIONID";

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    HttpServletRequest httpServletRequest;

    PersonService personalService;
    AuthService authService;

    @GetMapping
    public Iterable<Person> getAll() {
        checkAuthentication();

        return personalService.getPerson();
    }

    @GetMapping("/{id}")
    public PersonDto getPerson(@PathVariable(name = "id") Long id) throws NotFoundException {
        checkAuthentication();

        return personalService.getPerson(id);
    }

    @PostMapping
    public PersonDto createPersonal(@RequestBody PersonDto personDto) {
        checkAuthentication();

        return personalService.createPerson(personDto);
    }

    @PutMapping("/{id}")
    public PersonDto updatePersonal(@RequestBody PersonDto personDto, @PathVariable("id") Long id) throws NotFoundException {
        checkAuthentication();

        return personalService.updatePerson(personDto, id);
    }

    @DeleteMapping("/{id}")
    public void deletePersonal(@PathVariable(name = "id") Long id) throws NotFoundException {
        checkAuthentication();

        personalService.deletePerson(id);
    }

    private void checkAuthentication() {

        if (httpServletRequest.getCookies() == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "sessionId empty");
        }

        Optional<Cookie> sessionIdCookie = Arrays.stream(httpServletRequest.getCookies()).
                filter(cookie -> SESSION_ID_COOKIE.equals(cookie.getName()))
                .findFirst();

        if (!sessionIdCookie.isPresent() || StringUtils.isBlank(sessionIdCookie.get().getValue())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "sessionId empty");
        }

        try {
            authService.checkAuthentication(sessionIdCookie.get().getValue());
        } catch (HttpClientErrorException exception) {
            if (HttpStatus.UNAUTHORIZED == exception.getStatusCode()) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

}
