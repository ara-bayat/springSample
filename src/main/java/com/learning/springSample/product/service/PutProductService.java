package com.learning.springSample.product.service;


import com.learning.springSample.product.Command;
import com.learning.springSample.product.NotFoundException;
import com.learning.springSample.product.dto.EditProductDto;
import com.learning.springSample.product.dto.Product;
import com.learning.springSample.product.dto.ProductDto;
import com.learning.springSample.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PutProductService implements Command<EditProductDto, ProductDto> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<ProductDto> execute(EditProductDto input) {
        return productRepository.findById(input.getId()).map(
                item -> {
                    input.getProduct().setId(input.getId());
                    productRepository.save(item);
                    return ResponseEntity.status(HttpStatus.OK).body(new ProductDto(input.getProduct()));
                }

        ).orElseThrow(
                NotFoundException::new
        );
    }
}
