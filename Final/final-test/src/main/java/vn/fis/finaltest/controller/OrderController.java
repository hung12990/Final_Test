package vn.fis.finaltest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import vn.fis.finaltest.dto.*;
import vn.fis.finaltest.service.OrderService;

@RestController
@RequestMapping("/api/order")
@Slf4j
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{pageNumber}/{pageSize}")
    public Page<OrderDTO> findAll(@PathVariable(name="pageNumber") Integer pageNumber,
                                  @PathVariable(name="pageSize") Integer pageSize) {
        log.info("Request All Order. PageNumber: {}, PageSize: {}", pageNumber, pageSize);
        return orderService.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @GetMapping("/{orderId}")
    public OrderDTO findById(@PathVariable(name = "orderId") Long orderId) {
        return orderService.findById(orderId);
    }

    @PutMapping
    public OrderDTO createOrder(@RequestBody CreateOrderDTO createOrderDTO) {
        return orderService.createOrder(createOrderDTO);
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable(name = "orderId") Long orderId) {
        orderService.deleteOrderById(orderId);
    }

    @PostMapping("/addOrderItem")
    public OrderDTO addOrderItem(@RequestBody AddOrderItemDTO addOrderItemDTO) {
        return orderService.addOrderItem(addOrderItemDTO);
    }

    @PostMapping("/removeOrderItem")
    public OrderDTO removeOrderItem(@RequestBody RemoveItemDTO removeItemDTO) {
        return orderService.removeOrderItem(removeItemDTO);
    }

    @PostMapping("paid/{orderId}")
    public OrderDTO convertCreatedToPaid(@PathVariable(name = "orderId") Long orderId) {
        return orderService.convertCreatedToPaid(orderId);
    }

    @PostMapping("cancel/{orderId}")
    public OrderDTO convertCreatedToCancelled(@PathVariable(name = "orderId") Long orderId) {
        return orderService.convertCreatedToCancelled(orderId);
    }
}
