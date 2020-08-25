package com.cp.k8.ecom.payment.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Data
public class PaymentDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer paymentId;
    private Integer orderId;
    private Integer customerId;
    private BigDecimal total;
    private String status;
    private String transactionReference;
    private String paymentType;
}
