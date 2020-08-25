package com.cp.k8.ecom.catalog.dto;

import lombok.Data;

@Data
public class ServiceResponseDTO {
    String status;
    String message;
    Object body;
}
