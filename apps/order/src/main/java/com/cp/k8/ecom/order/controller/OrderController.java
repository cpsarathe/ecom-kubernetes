package com.cp.k8.ecom.order.controller;

import com.cp.k8.ecom.order.domain.Order;
import com.cp.k8.ecom.order.dto.OrderDTO;
import com.cp.k8.ecom.order.dto.PaymentDTO;
import com.cp.k8.ecom.order.dto.ServiceResponseDTO;
import com.cp.k8.ecom.order.repository.OrderRepository;
import com.cp.k8.ecom.order.service.PaymentService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Calendar;

@RestController
@CommonsLog
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/amiconnected")
    public String amIConnected() {
        return "amiconnected:yes";
    }

    @RequestMapping(value = "/place-order", method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponseDTO placeOrder(@RequestBody OrderDTO orderDTO, HttpServletRequest request) {
        ServiceResponseDTO response = new ServiceResponseDTO();
        log.info("begin placing order for customer:" + orderDTO.getCustomerId());
        response.setStatus("success");
        response.setMessage("");

        Order order = convertOrderDTOToOrder(orderDTO);
        order.setTotal(order.getItemSalePrice().multiply(new BigDecimal(order.getItemQuantity())));
        order.setStatus("IN_PROCESS");
        order.setCreatedDate(Calendar.getInstance().getTime());
        order.setUpdatedDate(Calendar.getInstance().getTime());
        Order savedOrder = orderRepository.save(order);
        log.info("Order is generated with id:" + order.getId());

        PaymentDTO paymentDTO = paymentService.pay(savedOrder);
        log.info("Order payment is done with reference:" + order.getPaymentReference());

        savedOrder.setPaymentReference(paymentDTO.getTransactionReference());
        savedOrder.setStatus("SUBMITTED");
        savedOrder.setUpdatedDate(Calendar.getInstance().getTime());
        orderRepository.save(savedOrder);
        log.info("Order is successfully placed for orderId:" + order.getId());

        response.setBody(convertOrderToOrderDTO(savedOrder));
        log.info("finished placing order for customer : " + order.getCustomerId() + " . Order reference is :" + savedOrder.getId());

        return response;
    }

    private Order convertOrderDTOToOrder(OrderDTO orderDTO) {
        return Order.builder().itemId(orderDTO.getItemId())
                .itemSalePrice(orderDTO.getItemPrice())
                .itemQuantity(orderDTO.getItemQuantity())
                .paymentType(orderDTO.getPaymentType())
                .customerId(orderDTO.getCustomerId())
                .paymentReference(null)
                .build();
    }

    private OrderDTO convertOrderToOrderDTO(Order order) {
        return OrderDTO.builder().customerId(order.getCustomerId())
                .paymentType(order.getPaymentType())
                .status(order.getStatus())
                .itemId(order.getItemId())
                .itemPrice(order.getItemSalePrice())
                .itemQuantity(order.getItemQuantity())
                .id(order.getId())
                .total(order.getTotal())
                .paymentReference(order.getPaymentReference())
                .build();
    }

}
