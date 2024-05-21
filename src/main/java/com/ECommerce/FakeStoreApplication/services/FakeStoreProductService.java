package com.ECommerce.FakeStoreApplication.services;

import com.ECommerce.FakeStoreApplication.dtos.FakeStoreProductDto;
import com.ECommerce.FakeStoreApplication.dtos.ProductRequestDto;
import com.ECommerce.FakeStoreApplication.dtos.ProductResponseDto;
import com.ECommerce.FakeStoreApplication.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private final String getProductsURL = "https://fakestoreapi.com/products";
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
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
    public ProductResponseDto getProductById(Long id) {
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(getProductsURL + "/{id}",
                                                                                 FakeStoreProductDto.class, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return this.getProductDtoFromFakeStoreProductDto(fakeStoreProductDto);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(this.getProductsURL,
                                                                                  FakeStoreProductDto[].class);
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : response.getBody()){
            productResponseDtoList.add(this.getProductDtoFromFakeStoreProductDto(fakeStoreProductDto));
        }
        return productResponseDtoList;
    }

    @Override
    public ProductResponseDto createProduct(ProductResponseDto productResponseDto) {
        return null;
    }

    @Override
    public ProductResponseDto deleteProduct(Long id) {
        return null;
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) {
        return null;
    }
}
