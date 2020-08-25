package com.cp.k8.ecom.customer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "customer")
@Data
@Builder
@AllArgsConstructor
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    public Customer(){

    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    @JoinColumn(name = "address_id")
    @OneToOne(targetEntity = Address.class)
    protected Address address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return new EqualsBuilder()
                .append(id, customer.id)
                .append(name, customer.name)
                .append(email, customer.email)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(email)
                .toHashCode();
    }
}
