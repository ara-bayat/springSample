package com.learning.springSample.product.service;


import com.learning.springSample.product.Command;
import com.learning.springSample.product.dto.ProductDto;
import com.learning.springSample.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetProductsService implements Command<Void,List<ProductDto>> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Cacheable("products")
    public ResponseEntity<List<ProductDto>> execute(Void input) {
        return ResponseEntity.status(HttpStatus.OK).body(
                productRepository.findAll()
                        .stream().map(ProductDto::new).toList()
        );
    }
}