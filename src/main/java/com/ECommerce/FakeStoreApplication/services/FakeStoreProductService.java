package com.ECommerce.FakeStoreApplication.services;

import com.ECommerce.FakeStoreApplication.dtos.FakeStoreProductDto;
import com.ECommerce.FakeStoreApplication.dtos.ProductRequestDto;
import com.ECommerce.FakeStoreApplication.dtos.ProductResponseDto;
import com.ECommerce.FakeStoreApplication.exceptions.ProductNotFoundException;
import com.ECommerce.FakeStoreApplication.thirdParty.productService.ThirdPartyProductServiceClient;
import com.ECommerce.FakeStoreApplication.thirdParty.productService.fakeStore.FakeStoreProductServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private final String getProductsURL = "https://fakestoreapi.com/products";
    private RestTemplateBuilder restTemplateBuilder;
    private ThirdPartyProductServiceClient thirdPartyProductServiceClient;

    @Autowired
    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient,
                                   RestTemplateBuilder restTemplateBuilder){
        this.thirdPartyProductServiceClient = fakeStoreProductServiceClient;
        this.restTemplateBuilder = restTemplateBuilder;
    }

    private ProductResponseDto getProductDtoFromFakeStoreProductDto(FakeStoreProductDto fakeStoreProductDto){
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(fakeStoreProductDto.getId());
        productResponseDto.setTitle(fakeStoreProductDto.getTitle());
        productResponseDto.setPrice(fakeStoreProductDto.getPrice());
        productResponseDto.setImage(fakeStoreProductDto.getImage());
        productResponseDto.setCategory(fakeStoreProductDto.getCategory());
        productResponseDto.setDescription(fakeStoreProductDto.getDescription());
        return productResponseDto;
    }

    @Override
    public ProductResponseDto getProductById(Long id) throws ProductNotFoundException {
        return this.thirdPartyProductServiceClient.getProductById(id);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        System.out.println("service : getAllProducts");
        return this.thirdPartyProductServiceClient.getAllProducts();
    }

    @Override
    public ProductResponseDto createProduct(ProductResponseDto productResponseDto) {
        return this.thirdPartyProductServiceClient.createProduct(productResponseDto);
    }

    @Override
    public boolean deleteProduct(Long id) {
        return this.thirdPartyProductServiceClient.deleteProduct(id);
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) {
        return null;
    }
}
