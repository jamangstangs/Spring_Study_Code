package jpabook.jpashop.api;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


// #1, ToOne 관계에 대해서 어떻게 성능을 최적화하는지 알아보자.
// ManyToOne, OneToOne
// Order 를 조회
// Order -> Member
// Order -> Delivery
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    // 단순하게 orders를 조회하자.
    // 엔티티를 그대로 반환하는 안좋은 예제
    // 무한 루프에 빠진다. order->member->order->member
    // 양방향 연관관계의 문제이다.
    @GetMapping("/api/v1/simple-orders")
    public List<Order> orderV1() {

        // Order를 가지고 와도,
        List<Order> all = orderRepository.findAllByCriteria(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName();        // Lazy 강제 초기화
            order.getDelivery().getAddress();   // Lazy 강제 초기화
        }
        return all;

    }
}
