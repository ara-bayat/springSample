package com.learning.springSample.product.repository;

import com.learning.springSample.product.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
