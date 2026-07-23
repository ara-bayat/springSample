package com.learning.springSample.product.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class user {
    @Id
    @NotNull
    @Size(min = 2, max = 200)
    private String username;
    @NotNull
    @Size(min = 2, max = 200)
    private String password;

}
