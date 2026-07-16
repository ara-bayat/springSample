package com.learning.springSample.product;


import com.learning.springSample.product.dto.Product;
import com.learning.springSample.product.service.CreateProductService;
import com.learning.springSample.product.service.DeleteProductService;
import com.learning.springSample.product.service.GetProductService;
import com.learning.springSample.product.service.PutProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Products {

    private final CreateProductService createProductService;
    private final DeleteProductService deleteProductService;
    private final GetProductService getProductService;
    private final PutProductService putProductService;

    public Products(CreateProductService createProductService, DeleteProductService deleteProductService, GetProductService getProductService, PutProductService putProductService) {
        this.createProductService = createProductService;
        this.deleteProductService = deleteProductService;
        this.getProductService = getProductService;
        this.putProductService = putProductService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return getProductService.execute(null);
    }

    @PostMapping
    public ResponseEntity<String> addProduct() {
        return createProductService.execute(null);
    }

    @PutMapping
    public ResponseEntity<String> editProduct() {
        return putProductService.execute(null);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct() {
        return deleteProductService.execute(null);
    }
}
