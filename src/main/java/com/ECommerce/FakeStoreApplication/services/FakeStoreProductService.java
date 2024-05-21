package com.ECommerce.FakeStoreApplication.services;

import com.ECommerce.FakeStoreApplication.dtos.FakeStoreProductDto;
import com.ECommerce.FakeStoreApplication.dtos.ProductRequestDto;
import com.ECommerce.FakeStoreApplication.dtos.ProductResponseDto;
import com.ECommerce.FakeStoreApplication.models.Category;
import com.ECommerce.FakeStoreApplication.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private final String getProductsURL = "https://fakestoreapi.com/products";
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    private Product getProductFromProductResponseDto(ProductResponseDto productResponseDto){
        Product product = new Product();
        product.setDescription(productResponseDto.getDescription());
        product.setId(productResponseDto.getId());
        product.setPrice(productResponseDto.getPrice());
        product.setName(productResponseDto.getTitle());
        product.setImageLink(productResponseDto.getImage());
        product.setCategory(new Category());
        product.getCategory().setCategoryName(productResponseDto.getCategory());
        return product;
    }

    @Override
    public Product getProductById(Long id) {
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(getProductsURL + "/{id}", FakeStoreProductDto.class, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(fakeStoreProductDto.getId());
        productResponseDto.setTitle(fakeStoreProductDto.getTitle());
        productResponseDto.setPrice(fakeStoreProductDto.getPrice());
        productResponseDto.setImage(fakeStoreProductDto.getImage());
        productResponseDto.setCategory(fakeStoreProductDto.getCategory());
        productResponseDto.setDescription(fakeStoreProductDto.getDescription());
        return this.getProductFromProductResponseDto(productResponseDto);
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(ProductResponseDto productResponseDto) {
        return null;
    }

    @Override
    public Product deleteProduct(Long id) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, ProductRequestDto productRequestDto) {
        return null;
    }
}
