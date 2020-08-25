package com.cp.k8.ecom.customer.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
public class CustomerDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String email;
    private String mobile;
    private Date dateOfBirth;
    private AddressDTO address;
    @JsonIgnore
    private String message;
}
