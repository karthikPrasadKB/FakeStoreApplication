package com.ECommerce.FakeStoreApplication.services;


import com.ECommerce.FakeStoreApplication.dtos.ProductRequestDto;
import com.ECommerce.FakeStoreApplication.dtos.ProductResponseDto;
import com.ECommerce.FakeStoreApplication.models.Product;

import java.util.List;

public interface ProductService {
    ProductResponseDto getProductById(Long id);
    List<ProductResponseDto> getAllProducts();
    ProductResponseDto createProduct(ProductResponseDto productResponseDto);
    ProductResponseDto deleteProduct(Long id);
    ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto);
}
