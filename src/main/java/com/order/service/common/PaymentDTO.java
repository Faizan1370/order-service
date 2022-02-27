package com.order.service.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDTO {

    private Long paymentId;
    private String paymentStatus;
    private String transactionId;
    private Long OrderId;
    private Double amount;

}
