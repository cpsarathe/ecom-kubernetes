package com.cp.k8.ecom.catalog.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "product")
@Builder
@Data
@AllArgsConstructor
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    public Product(){}

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "url")
    private String url;

    @Column(name = "retail_price")
    private BigDecimal retailPrice;

    @Column(name = "sale_price")
    private BigDecimal salePrice;

    @Column(name = "seller_id")
    private String sellerId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(id, product.id)
                .append(name, product.name)
                .append(url, product.url)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(url)
                .toHashCode();
    }
}
