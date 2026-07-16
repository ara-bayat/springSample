package com.learning.springSample.product.service;


import com.learning.springSample.product.Command;
import com.learning.springSample.product.dto.ProductDto;
import com.learning.springSample.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteProductService implements Command<Integer,Void> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<Void> execute(Integer id) {
        var product=productRepository.findById(id);
        if(product.isPresent()){
            productRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
