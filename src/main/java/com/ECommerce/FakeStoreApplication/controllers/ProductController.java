package com.ECommerce.FakeStoreApplication.controllers;

import com.ECommerce.FakeStoreApplication.dtos.ProductRequestDto;
import com.ECommerce.FakeStoreApplication.dtos.ProductResponseDto;
import com.ECommerce.FakeStoreApplication.models.Product;
import com.ECommerce.FakeStoreApplication.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return List.of();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return this.productService.getProductById(id);
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
