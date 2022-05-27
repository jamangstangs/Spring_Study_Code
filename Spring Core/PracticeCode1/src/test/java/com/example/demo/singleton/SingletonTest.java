package com.example.demo.singleton;

import com.example.demo.Appconfig;
import com.example.demo.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {

    @Test
    @DisplayName("Spring 없는 순수 DI 컨테이너")
    void pureContainer() {
        Appconfig appConfig = new Appconfig();

        // 호출할때마다 memberService 생성
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        // 이들의 참조값을 확인해보자.
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // MemberService1 != MemberService2
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);

    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonService(){
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
    }
}