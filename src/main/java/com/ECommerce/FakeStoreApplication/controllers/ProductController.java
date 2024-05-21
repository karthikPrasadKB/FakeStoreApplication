package com.ECommerce.FakeStoreApplication.controllers;

import com.ECommerce.FakeStoreApplication.dtos.ExceptionDto;
import com.ECommerce.FakeStoreApplication.dtos.ProductRequestDto;
import com.ECommerce.FakeStoreApplication.dtos.ProductResponseDto;
import com.ECommerce.FakeStoreApplication.exceptions.ProductNotFoundException;
import com.ECommerce.FakeStoreApplication.models.Product;
import com.ECommerce.FakeStoreApplication.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    private ResponseEntity<ExceptionDto> handleProductNotFoundException(ProductNotFoundException productNotFoundException){
        return new ResponseEntity<>(new ExceptionDto(HttpStatus.NOT_FOUND, productNotFoundException.getMessage()),
                                  HttpStatus.NOT_FOUND);
    }

    @GetMapping("/products")
    public List<ProductResponseDto> getAllProducts(){
        return this.productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ProductResponseDto getProductById(@PathVariable("id") Long id) throws ProductNotFoundException{
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
