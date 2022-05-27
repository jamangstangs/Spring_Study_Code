package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configuration() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(Appconfig.class);
        Appconfig bean = ac.getBean(Appconfig.class);

        System.out.println("bean = " + bean);
    }
}
