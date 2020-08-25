package com.cp.k8.ecom.order.repository;

import com.cp.k8.ecom.order.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    Order save(Order order);

    List<Order> findAll();

    Order findById(Integer orderId);
}
