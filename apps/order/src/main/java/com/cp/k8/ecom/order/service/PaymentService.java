package com.cp.k8.ecom.order.service;

import com.cp.k8.ecom.order.domain.Order;
import com.cp.k8.ecom.order.dto.PaymentDTO;
import com.cp.k8.ecom.order.logging.LoggingRequestInterceptor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@CommonsLog
public class PaymentService {

    @Value("${payment.api.endpoint}")
    private String paymentAPIEndPoint;
    private static final String AUTHORIZATION = "Authorization";

    public PaymentDTO pay(Order order) {
        log.info("starting payment for order:"+order.getId());
        HttpHeaders httpHeaders = getHttpHeader();
        httpHeaders.set(AUTHORIZATION, "Basic cGF5bWVudDpwYXltZW50");
        PaymentDTO paymentDTO = convertOrderToPaymentDTO(order);
        HttpEntity<PaymentDTO> httpEntity = new HttpEntity<>(paymentDTO, httpHeaders);
        ResponseEntity<PaymentDTO> response = getRestTemplate().postForEntity(paymentAPIEndPoint, httpEntity, PaymentDTO.class);
        PaymentDTO responsePaymentDTO = response.getBody();
        return responsePaymentDTO;
    }

    private HttpHeaders getHttpHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }

    private PaymentDTO convertOrderToPaymentDTO(Order order) {
        return PaymentDTO.builder().customerId(order.getCustomerId())
                .orderId(order.getId())
                .paymentType(order.getPaymentType())
                .total(order.getTotal()).build();
    }

    private RestTemplate getRestTemplate() {
        ClientHttpRequestInterceptor interceptor = new LoggingRequestInterceptor();
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(interceptor);
        RestTemplate template = new RestTemplate();
        template.setInterceptors(interceptors);
        return template;
    }
}
