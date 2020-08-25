package com.cp.k8.ecom.catalog.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class ProductDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private BigDecimal retailPrice;
    private BigDecimal salePrice;
    private String message;
    private String imageUrl;
    private String url;
    private int quantity;
    private String sellerId;
}
