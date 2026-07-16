package com.learning.springSample.product.security;

import com.learning.springSample.product.dto.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SecurityController {

    @GetMapping("open")
    public ResponseEntity<String> open() {
        return ResponseEntity.ok("this is open");
    }

    @GetMapping("close")
    public ResponseEntity<String> close() {
        return ResponseEntity.ok("this is close");
    }

    @GetMapping("special")
    public ResponseEntity<String> special() {
        return ResponseEntity.ok("this is special");
    }

    @GetMapping("basic")
    public ResponseEntity<String> basic() {
        return ResponseEntity.ok("this is basic");
    }
}
