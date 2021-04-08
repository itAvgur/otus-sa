package com.itavgur.otus.sa.auth.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.itavgur.otus.sa.auth.domain.User;
import com.itavgur.otus.sa.auth.repository.UsersRepository;
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

    UsersRepository usersRepository;
    IMap<String, Long> userSessions;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public SessionService(UsersRepository usersRepository, HazelcastInstance hazelcastAuthInstance) {
        this.usersRepository = usersRepository;
        userSessions = hazelcastAuthInstance.getMap("userSessions");

    }

    public String create(User userId) {

        String sessionId = UUID.randomUUID().toString();
        userSessions.put(sessionId, userId.getId(), Long.parseLong(sessionTtlMinutes), TimeUnit.MINUTES);
        return sessionId;

    }

    public Optional<User> findUser(String sessionId) {

        Long userId = userSessions.get(sessionId);
        return userId == null ? Optional.empty() : usersRepository.findById(userId);

    }

    public void remove(String sessionId) {

        userSessions.remove(sessionId);

    }
}
