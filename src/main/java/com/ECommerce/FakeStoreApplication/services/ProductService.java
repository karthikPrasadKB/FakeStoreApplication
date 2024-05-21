package com.ECommerce.FakeStoreApplication.services;


import com.ECommerce.FakeStoreApplication.dtos.ProductRequestDto;
import com.ECommerce.FakeStoreApplication.dtos.ProductResponseDto;
import com.ECommerce.FakeStoreApplication.exceptions.ProductNotFoundException;

import java.util.List;

public interface ProductService {
    ProductResponseDto getProductById(Long id) throws ProductNotFoundException;
    List<ProductResponseDto> getAllProducts();
    ProductResponseDto createProduct(ProductResponseDto productResponseDto);
    boolean deleteProduct(Long id);
    ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto);
}
