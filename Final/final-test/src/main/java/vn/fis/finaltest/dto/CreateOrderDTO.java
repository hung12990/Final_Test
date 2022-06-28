package vn.fis.finaltest.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderDTO {
    private Long customerId;
    private List<OrderItemDTO> orderItems;
}
