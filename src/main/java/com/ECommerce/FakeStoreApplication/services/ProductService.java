package com.ECommerce.FakeStoreApplication.services;


import com.ECommerce.FakeStoreApplication.dtos.ProductRequestDto;
import com.ECommerce.FakeStoreApplication.dtos.ProductResponseDto;
import com.ECommerce.FakeStoreApplication.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product createProduct(ProductResponseDto productResponseDto);
    Product deleteProduct(Long id);
    Product updateProduct(Long id, ProductRequestDto productRequestDto);
}
