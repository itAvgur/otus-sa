package com.itavgur.otus.sa.auth.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.itavgur.otus.sa.auth.domain.User;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SessionService {

    @Value("${hazelcast.session.ttl-min:30}")
    @NonFinal
    private String sessionTtlMinutes;

    IMap<String, String> userSessions;

    @Autowired
    public SessionService(HazelcastInstance hazelcastAuthInstance) {
        userSessions = hazelcastAuthInstance.getMap("userSessions");
    }

    public String create(User userId) {

        String sessionId = UUID.randomUUID().toString();
        userSessions.put(sessionId, userId.getLogin(), Long.parseLong(sessionTtlMinutes), TimeUnit.MINUTES);
        return sessionId;
    }

    public Optional<String> findUserLogin(String sessionId) {

        String userLogin = userSessions.get(sessionId);
        return userLogin == null ? Optional.empty() : Optional.of(userLogin);
    }

    public void remove(String sessionId) {
        userSessions.remove(sessionId);
    }
}
