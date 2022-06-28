package vn.fis.finaltest.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RemoveItemDTO {
    private Long orderId;
    private Long orderItemId;
}
