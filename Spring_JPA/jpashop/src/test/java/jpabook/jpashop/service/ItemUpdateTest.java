package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

@SpringBootTest
public class ItemUpdateTest {

    @Autowired
    EntityManager em;

    @Test
    public void updateTest() throws Exception {

        Book book = em.find(Book.class, 1L);

        book.setName("asdfasdf");

        // 변경 감지 = Dirty Checking : 이 원리로 기본적으로 JPA 원소를 바꾼다. flush 할 때 더티체킹이 발생한다.
        // 준영속 Entity -> 영속성 컨텍스트가 더는 관리하지않는, itemService.saveItem(book)에서 수정을 시도하는 Book 객체,
        // Book 객체 -> 이미 DB에 한 번 저장되어서 식별자가 존재한다. -> 임의로 만들어낸 엔티티도 기존 식별자를 가지고 있으면 준영속 엔티티로 볼 수 있다.





    }
}
