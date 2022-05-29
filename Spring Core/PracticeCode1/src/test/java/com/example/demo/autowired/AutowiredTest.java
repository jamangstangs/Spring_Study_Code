package com.example.demo.autowired;

import com.example.demo.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void autowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
    }

    static class TestConfig {
        @Autowired(required = false)
        public void setBean1(Member member) {
            System.out.println("member = " + member);
        }

        @Autowired
        public void setBean2(@Nullable Member member) {
            System.out.println("member = " + member);
        }

        @Autowired
        public void setBean3(Optional<Member> member) {
            System.out.println("member = " + member);
        }
    }
}
