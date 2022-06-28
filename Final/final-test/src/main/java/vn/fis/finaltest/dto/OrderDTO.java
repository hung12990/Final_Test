package vn.fis.finaltest.dto;

import lombok.*;
import vn.fis.finaltest.model.Customer;
import vn.fis.finaltest.model.Order;
import vn.fis.finaltest.model.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {
    private Long id;
    private LocalDateTime orderDateTime;
    private Double totalAmount;
    private Status status;
    private Long customerId;
    private List<Long> orderItemIds;

    public static class Mapper{
        public static OrderDTO fromEntity(Order order) {
            return OrderDTO.builder()
                    .id(order.getId())
                    .orderDateTime(order.getOrderDateTime())
                    .totalAmount(order.getTotalAmount())
                    .status(order.getStatus())
                    .customerId(order.getCustomer().getId())
                    .orderItemIds(order.getOrderItems().stream().map(orderItem
                            -> orderItem.getId()).collect(Collectors.toList()))
                    .build();
        }
    }
}
