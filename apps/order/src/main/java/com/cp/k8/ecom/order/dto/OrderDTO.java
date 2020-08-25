package com.cp.k8.ecom.order.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Data
public class OrderDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer customerId;
    private String itemId;
    private BigDecimal itemPrice;
    private Integer itemQuantity;
    private BigDecimal total;
    private String status;
    private String paymentType;
    private String paymentReference;
}
