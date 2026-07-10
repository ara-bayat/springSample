package com.learning.springSample.product;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetProductService implements Command<Void,String> {


    @Override
    public ResponseEntity<String> execute(Void input) {
        return ResponseEntity.status(HttpStatus.OK).body("get product");
    }
}