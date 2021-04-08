package com.itavgur.otus.sa.auth.hazelcast;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastAuthConfiguration {

    @Bean
    public HazelcastInstance hazelcastAuthInstance() {

        return Hazelcast.newHazelcastInstance();
    }

}
