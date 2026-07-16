package com.learning.springSample.product.service;


import com.learning.springSample.product.Query;
import com.learning.springSample.product.dto.ProductDto;
import com.learning.springSample.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service


public class FindProductsServiceByKeyword implements Query<String,List<ProductDto>> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<List<ProductDto>> execute(String input) {
        return ResponseEntity.status(HttpStatus.OK).body(
                productRepository.findByKeyword(input)
                        .stream().map(ProductDto::new).toList()
        );
    }
}