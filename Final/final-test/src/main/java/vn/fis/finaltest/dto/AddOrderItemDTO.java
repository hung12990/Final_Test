package vn.fis.finaltest.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddOrderItemDTO {
    private Long orderId;
    private Long productId;
    private Long quantity;
}
