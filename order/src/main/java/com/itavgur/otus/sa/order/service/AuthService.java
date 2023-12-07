package com.itavgur.otus.sa.order.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.itavgur.otus.sa.order.web.OrderController.SESSION_ID_COOKIE;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class AuthService {

    RestTemplate restTemplate;

    @Value("${auth.get-login.url}")
    @NonFinal
    String authCheckUrl;

    public String checkAuthentication(String sessionId) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Cookie", SESSION_ID_COOKIE + "=" + sessionId);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        return restTemplate.exchange(authCheckUrl, HttpMethod.GET, httpEntity, String.class).getBody();

    }
}