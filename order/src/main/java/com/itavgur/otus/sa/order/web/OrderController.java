package com.itavgur.otus.sa.order.web;

import com.itavgur.otus.sa.order.exception.NotFoundException;
import com.itavgur.otus.sa.order.service.AuthService;
import com.itavgur.otus.sa.order.service.OrderService;
import com.itavgur.otus.sa.order.service.PersonService;
import com.itavgur.otus.sa.order.web.dto.OrderDto;
import com.itavgur.otus.sa.order.web.dto.PersonDto;
import io.micrometer.common.util.StringUtils;
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
@RequestMapping("/order")
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController {

    public static final String SESSION_ID_COOKIE = "SESSIONID";

    HttpServletRequest httpServletRequest;

    OrderService orderService;
    AuthService authService;
    PersonService personService;

    @GetMapping("/{id}")
    public OrderDto getOrder(@PathVariable(name = "id") Integer orderId) throws NotFoundException {
        checkAuthentication();
        PersonDto personDto = personService.getPersonInfo(getSessionId());
        return orderService.getOrder(orderId, personDto);
    }

    @PostMapping
    public OrderDto createOrders(@RequestBody OrderDto orderDto) {
        checkAuthentication();
        PersonDto personDto = personService.getPersonInfo(getSessionId());
        return orderService.createOrder(orderDto, personDto);
    }

    @PutMapping
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        checkAuthentication();
        PersonDto personDto = personService.getPersonInfo(getSessionId());
        return orderService.updateOrder(orderDto, personDto);
    }

    @DeleteMapping("/{id}")
    public void deleteOrders(@PathVariable(name = "id") Integer orderId) {
        checkAuthentication();
        PersonDto personDto = personService.getPersonInfo(getSessionId());
        orderService.deleteOrder(orderId, personDto);
    }

    private String getSessionId() {

        if (httpServletRequest.getCookies() == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "sessionId empty");
        }

        Optional<Cookie> sessionId = Arrays.stream(httpServletRequest.getCookies()).
                filter(cookie -> SESSION_ID_COOKIE.equals(cookie.getName()))
                .findFirst();

        if (!sessionId.isPresent() || StringUtils.isBlank(sessionId.get().getValue())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "sessionId empty");
        }

        return sessionId.get().getValue();
    }

    private void checkAuthentication() {

        String sessionId = getSessionId();
        try {
            authService.checkAuthentication(sessionId);
        } catch (HttpClientErrorException exception) {
            if (HttpStatus.UNAUTHORIZED == exception.getStatusCode()) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

}