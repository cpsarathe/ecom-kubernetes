package com.cp.k8.ecom.customer.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class AddressDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String type;
    private String address;
}
