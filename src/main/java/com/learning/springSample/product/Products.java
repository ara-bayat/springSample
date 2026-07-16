package com.learning.springSample.product;


import com.learning.springSample.product.dto.EditProductDto;
import com.learning.springSample.product.dto.Product;
import com.learning.springSample.product.dto.ProductDto;
import com.learning.springSample.product.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Products {

     private static final Logger logger= LoggerFactory.getLogger(Products.class);

    private final CreateProductService createProductService;
    private final DeleteProductService deleteProductService;
    private final GetProductsService getProductsService;
    private final GetProductService getProductService;
    private final PutProductService putProductService;
    private final FindProductsService findProductsService;
    private final FindProductsServiceByKeyword findProductsServiceByKeyword;

    public Products(CreateProductService createProductService,
                    DeleteProductService deleteProductService,
                    GetProductsService getProductsService,
                    GetProductService getProductService,
                    PutProductService putProductService,
                    FindProductsService findProductsService,
                    FindProductsServiceByKeyword findProductsServiceByKeyword) {
        this.createProductService = createProductService;
        this.deleteProductService = deleteProductService;
        this.getProductsService = getProductsService;
        this.putProductService = putProductService;
        this.getProductService = getProductService;
        this.findProductsService = findProductsService;
        this.findProductsServiceByKeyword = findProductsServiceByKeyword;
    }

    @GetMapping("products")
    public ResponseEntity<List<ProductDto>> getProducts() {
        logger.error("getProducts");
        return getProductsService.execute(null);
    }
    @GetMapping("product/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable int id) {
        return getProductService.execute(id);
    }
    @GetMapping("product/search")
    public ResponseEntity<List<ProductDto>> getProductByName(@RequestParam String name) {
        return findProductsService.execute(name);
    }

    @GetMapping("product/keyword")
    public ResponseEntity<List<ProductDto>> getProductByKeyword(@RequestParam String keyword) {
        return findProductsServiceByKeyword.execute(keyword);
    }

    @PostMapping("product")
    public ResponseEntity<ProductDto> addProduct(@RequestBody Product product) {
        return createProductService.execute(product);
    }

    @PutMapping("product/{id}")
    public ResponseEntity<ProductDto> editProduct(@PathVariable int id,@RequestBody Product product) {

        return putProductService.execute(new EditProductDto(id,product));
    }

    @DeleteMapping("product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {

        return deleteProductService.execute(id);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse(e.getMessage()));
    }
}
