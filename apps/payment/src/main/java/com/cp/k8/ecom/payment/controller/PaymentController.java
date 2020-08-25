package com.cp.k8.ecom.payment.controller;

import com.cp.k8.ecom.payment.domain.Payment;
import com.cp.k8.ecom.payment.dto.PaymentDTO;
import com.cp.k8.ecom.payment.dto.ServiceResponseDTO;
import com.cp.k8.ecom.payment.repository.PaymentRepository;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.UUID;

@RestController
@CommonsLog
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping("/amiconnected")
    public String amIConnected() {
        return "amiconnected:yes";
    }

    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    @ResponseBody
    public PaymentDTO pay(@RequestBody PaymentDTO paymentDTO, HttpServletRequest request) {
        ServiceResponseDTO response = new ServiceResponseDTO();
        log.info("begin payment for order :" + paymentDTO.getOrderId());
        response.setStatus("success");
        response.setMessage("");

        Payment payment = convertPaymentDTOToPayment(paymentDTO);
        payment.setCreatedDate(Calendar.getInstance().getTime());
        payment.setUpdatedDate(Calendar.getInstance().getTime());
        Payment savedPayment = paymentRepository.save(payment);
        log.info("Payment is succeed with reference:" + savedPayment.getTransactionReference());
        return convertPaymentToPaymentDTO(savedPayment);
    }

    private Payment convertPaymentDTOToPayment(PaymentDTO paymentDTO) {
        return Payment.builder().customerId(paymentDTO.getCustomerId())
                .orderId(paymentDTO.getOrderId())
                .paymentType(paymentDTO.getPaymentType())
                .total(paymentDTO.getTotal())
                .transactionReference(UUID.randomUUID().toString())
                .status("PAID").build();
    }

    private PaymentDTO convertPaymentToPaymentDTO(Payment payment) {
        return PaymentDTO.builder().customerId(payment.getCustomerId())
                .orderId(payment.getOrderId())
                .paymentType(payment.getPaymentType())
                .status(payment.getStatus())
                .transactionReference(payment.getTransactionReference())
                .total(payment.getTotal())
                .paymentId(payment.getId())
                .build();
    }

}
