package com.ECommerce.FakeStoreApplication.controllers;

import com.ECommerce.FakeStoreApplication.dtos.ProductRequestDto;
import com.ECommerce.FakeStoreApplication.dtos.ProductResponseDto;
import com.ECommerce.FakeStoreApplication.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return List.of();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return null;
    }

    @PostMapping("/products")
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto productRequestDto){
        return null;
    }

    @PatchMapping("/products/{id}")
    public ProductResponseDto updateProduct(@PathVariable("id") Long id,
                                            @RequestBody ProductRequestDto productRequestDto){
        return null;
    }

    @DeleteMapping("/products/{id}")
    public boolean deleteProductById(@PathVariable("id") Long id){
        return false;
    }
}
