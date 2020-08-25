package com.cp.k8.ecom.order.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "order_info")
@Data
@Builder
@AllArgsConstructor
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "item_id")
    private String itemId;

    @Column(name = "item_sale_price")
    private BigDecimal itemSalePrice;

    @Column(name = "item_quantity")
    private Integer itemQuantity;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "status")
    private String status;

    @Column(name = "payment_reference")
    private String paymentReference;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

}
