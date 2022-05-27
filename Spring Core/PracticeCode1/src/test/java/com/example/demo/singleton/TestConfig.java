package com.example.demo.singleton;

import org.springframework.context.annotation.Bean;

public class TestConfig {

    @Bean
    public StatefulService statefulService() {
        return new StatefulService();

    }
}
