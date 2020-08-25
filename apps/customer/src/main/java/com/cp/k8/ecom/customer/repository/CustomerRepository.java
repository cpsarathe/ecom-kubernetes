package com.cp.k8.ecom.customer.repository;

import com.cp.k8.ecom.customer.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer save(Customer product);

    List<Customer> findAll();

    Customer findById(int customerId);
}
