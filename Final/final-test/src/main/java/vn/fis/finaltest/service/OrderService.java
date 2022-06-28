package vn.fis.finaltest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.fis.finaltest.dto.*;

public interface OrderService {
    Page<OrderDTO> findAll(Pageable pageable);

    OrderDTO findById(Long orderId);

    OrderDTO createOrder(CreateOrderDTO createOrderDTO);

    void deleteOrderById(Long orderId);

    OrderDTO addOrderItem(AddOrderItemDTO addOrderItemDTO);

    OrderDTO removeOrderItem(RemoveItemDTO removeItemDTO);

    OrderDTO convertCreatedToPaid(Long orderId);

    OrderDTO convertCreatedToCancelled(Long orderId);
}
