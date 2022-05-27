package com.example.demo.beanfind;

import com.example.demo.discount.DiscountPolicy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("동일한 타입을 가진 Bean들을 조회할 경우")
    void findBeanByTypeDuplicate() {
        Assertions.assertThatThrownBy(() -> {
            DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
        }).isInstanceOf(NoUniqueBeanDefinitionException.class);
    }
}
