package com.learning.springSample.product;


import com.learning.springSample.product.dto.EditProductDto;
import com.learning.springSample.product.dto.Product;
import com.learning.springSample.product.dto.ProductDto;
import com.learning.springSample.product.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Products {

    private final CreateProductService createProductService;
    private final DeleteProductService deleteProductService;
    private final GetProductsService getProductsService;
    private final GetProductService getProductService;
    private final PutProductService putProductService;

    public Products(CreateProductService createProductService,
                    DeleteProductService deleteProductService,
                    GetProductsService getProductsService,
                    GetProductService getProductService,
                    PutProductService putProductService) {
        this.createProductService = createProductService;
        this.deleteProductService = deleteProductService;
        this.getProductsService = getProductsService;
        this.putProductService = putProductService;
        this.getProductService = getProductService;
    }

    @GetMapping("products")
    public ResponseEntity<List<ProductDto>> getProducts() {
        return getProductsService.execute(null);
    }
    @GetMapping("product/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable int id) {
        return getProductService.execute(id);
    }

    @PostMapping
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
}
