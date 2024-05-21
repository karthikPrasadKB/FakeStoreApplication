package com.ECommerce.FakeStoreApplication.services;


import com.ECommerce.FakeStoreApplication.dtos.ProductRequestDto;
import com.ECommerce.FakeStoreApplication.dtos.ProductResponseDto;
import com.ECommerce.FakeStoreApplication.exceptions.ProductNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ProductResponseDto getProductById(Long id) throws ProductNotFoundException;
    List<ProductResponseDto> getAllProducts();
    ProductResponseDto createProduct(ProductRequestDto productRequestDto);
    boolean deleteProduct(Long id);
    void updateProduct(Long id, ProductRequestDto productRequestDto) throws ProductNotFoundException;
}
