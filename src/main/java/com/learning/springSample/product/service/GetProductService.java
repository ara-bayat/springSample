package com.learning.springSample.product.service;


import com.learning.springSample.product.Command;
import com.learning.springSample.product.dto.Product;
import com.learning.springSample.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetProductService implements Command<Void,List<Product>> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<List<Product>> execute(Void input) {
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
    }
}