package com.learning.springSample.product.service;


import com.learning.springSample.product.Command;
import com.learning.springSample.product.dto.ProductDto;
import com.learning.springSample.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetProductService implements Command<Integer,ProductDto> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<ProductDto> execute(Integer id) {
        var products = productRepository.findById(id);
        return products.map(product -> ResponseEntity.status(HttpStatus.OK).body(
                new ProductDto(product)
        )).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}