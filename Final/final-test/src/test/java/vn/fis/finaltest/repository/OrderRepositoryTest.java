package vn.fis.finaltest.repository;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.fis.finaltest.model.Customer;
import vn.fis.finaltest.model.Order;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    void testFindAllOrder() {
        List<Order> orders = orderRepository.findAll();
        log.info("Size Order: {}", orders.size());
        orders.forEach(order -> log.info("Order: {}",order));
        Assertions.assertThat(2).isEqualTo(orders.size());
    }

    @Test
    void testFindOrderById() {
        Order order = orderRepository.findById(1L).get();
        log.info("Order: {}", order);
    }
}