package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryDto;
import jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


// #1, ToOne 관계에 대해서 어떻게 성능을 최적화하는지 알아보자.
// ManyToOne, OneToOne
// Order 를 조회
// Order -> Member
// Order -> Delivery
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

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

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> orderV2() {
        // N+1 -> 1 + N 문제, 쿼리 1번 실행했는데, 첫번째 N번 쿼리만큰 N개의 데이터가 조회된다.
        // 1 + N + N -> 현재는 이 문제다. 근데 데이터가 2번 조회되니까 N = 2
        // 1 + N + N  = 5

        // 첫번째 N : Member n 번
        // 두번째 N : Delibery n 번

        // Eager로 바꿔도 소용없다.

        // SQL 1회 실행 이후, Order가 2개 잇으니까, 아래 루프를 2번 돈다.
        return orderRepository.findAllByCriteria(new OrderSearch()).stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());
        // 2번째 돌 때 Lazy 로딩 초기화,
    }

    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> orderV3() {
        // 필요한 것만 fetch Join으로
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        List<SimpleOrderDto> result = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());
        return result;
    }

    @GetMapping("/api/v4/simple-orders")
    public List<OrderSimpleQueryDto> ordersV4() {
        return orderSimpleQueryRepository.findOrderDtos();
    }

    @Data
    static class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
        }
    }

}
