package com.cp.k8.ecom.payment.repository;

import com.cp.k8.ecom.payment.domain.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long> {

    Payment save(Payment payment);

    List<Payment> findAll();

    Payment findByTransactionReference(String transactionReference);
}
