package com.cp.k8.ecom.customer.repository;

import com.cp.k8.ecom.customer.domain.Address;
import com.cp.k8.ecom.customer.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

    Address save(Address address);

    List<Address> findAll();

    Address findById(int addressId);
}
