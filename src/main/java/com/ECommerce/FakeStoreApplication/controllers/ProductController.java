package com.ECommerce.FakeStoreApplication.controllers;

import com.ECommerce.FakeStoreApplication.dtos.ProductRequestDto;
import com.ECommerce.FakeStoreApplication.dtos.ProductResponseDto;
import com.ECommerce.FakeStoreApplication.exceptions.ProductNotFoundException;
import com.ECommerce.FakeStoreApplication.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResponseDto> getAllProducts(){
        return this.productService.getAllProducts();
    }

    @GetMapping("{id}")
    public ProductResponseDto getProductById(@PathVariable("id") Long id) throws ProductNotFoundException{
        return this.productService.getProductById(id);
    }

    @PostMapping
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto productRequestDto){
        return this.productService.createProduct(productRequestDto);
    }

    @PutMapping("{id}")
    public HttpStatus updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequestDto productRequestDto)
            throws ProductNotFoundException{
        this.productService.updateProduct(id, productRequestDto);
        return HttpStatus.ACCEPTED;
    }

    @DeleteMapping("{id}")
    public boolean deleteProductById(@PathVariable("id") Long id){
        return false;
    }
}
