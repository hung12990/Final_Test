package vn.fis.finaltest.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import vn.fis.finaltest.dto.*;
import vn.fis.finaltest.service.OrderService;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;


    @Test
    void findAll() {
        Page<OrderDTO> orderDTOs = orderService.findAll(PageRequest.of(0,2));
        orderDTOs.forEach(orderDTO ->  log.info("orderDTO: {}",orderDTO));
        assertEquals(2,orderDTOs.getSize());
    }

    @Test
    void findById() {
        OrderDTO orderDTO = orderService.findById(1L);
        log.info("Order: {}",orderDTO);
    }

    @Test
    void createOrder() {
        OrderItemDTO o1 = OrderItemDTO.builder()
                .productId(1L)
                .quantity(2L)
                .build();
        OrderItemDTO o2 = OrderItemDTO.builder()
                .productId(2L)
                .quantity(1L)
                .build();

        CreateOrderDTO createOrderDTO = CreateOrderDTO.builder()
                .customerId(3L)
                .orderItems(new ArrayList<>(Arrays.asList(o1,o2)))
                .build();
        orderService.createOrder(createOrderDTO);

        OrderDTO orderDTO = orderService.findById(6L);
        assertEquals(100,orderDTO.getTotalAmount(),0.0000000001);
    }

    @Test
    void deleteOrderById() {
        orderService.deleteOrderById(6L);
    }

    @Test
    void addOrderItem() {
        AddOrderItemDTO addOrderItemDTO = AddOrderItemDTO.builder()
                .orderId(1L)
                .productId(1L)
                .quantity(1L)
                .build();
        orderService.addOrderItem(addOrderItemDTO);

        OrderDTO orderDTO = orderService.findById(1L);

        assertEquals(130,orderDTO.getTotalAmount(),0.00000000001);
    }

    @Test
    void removeOrderItem() {
        RemoveItemDTO removeItemDTO = RemoveItemDTO.builder()
                .orderId(1L)
                .orderItemId(15L)
                .build();
        orderService.removeOrderItem(removeItemDTO);
        OrderDTO orderDTO = orderService.findById(1L);
        assertEquals(70,orderDTO.getTotalAmount(),0.00000000001);
    }

    @Test
    void convertCreatedToPaid() {
        orderService.convertCreatedToPaid(5L);
        OrderDTO orderDTO = orderService.findById(5L);
        assertEquals("PAID",orderDTO.getStatus().toString());
    }

    @Test
    void convertCreatedToCancelled() {
        orderService.convertCreatedToCancelled(6L);
        OrderDTO orderDTO = orderService.findById(6L);
        assertEquals("CANCELLED",orderDTO.getStatus().toString());
    }
}