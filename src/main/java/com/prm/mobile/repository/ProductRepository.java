package com.prm.mobile.repository;

import com.prm.mobile.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByProductType(String productType);
}
