package com.learning.springSample.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EditProductDto {
    private Integer id;
    private Product product;

}
