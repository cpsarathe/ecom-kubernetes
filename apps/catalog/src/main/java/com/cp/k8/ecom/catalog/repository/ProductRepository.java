package com.cp.k8.ecom.catalog.repository;

import com.cp.k8.ecom.catalog.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Product save(Product product);

    List<Product> findAll();

    Product findById(String productId);
}
