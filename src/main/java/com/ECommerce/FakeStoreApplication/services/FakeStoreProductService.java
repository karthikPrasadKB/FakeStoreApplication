package com.ECommerce.FakeStoreApplication.services;

import com.ECommerce.FakeStoreApplication.dtos.FakeStoreProductDto;
import com.ECommerce.FakeStoreApplication.dtos.ProductRequestDto;
import com.ECommerce.FakeStoreApplication.dtos.ProductResponseDto;
import com.ECommerce.FakeStoreApplication.exceptions.ProductNotFoundException;
import com.ECommerce.FakeStoreApplication.thirdParty.productService.ThirdPartyProductServiceClient;
import com.ECommerce.FakeStoreApplication.thirdParty.productService.fakeStore.FakeStoreProductServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private final String getProductsURL = "https://fakestoreapi.com/products";
    private RestTemplateBuilder restTemplateBuilder;
    private ThirdPartyProductServiceClient thirdPartyProductServiceClient;
    private RedisTemplate<String, Object> redisTemplate;
    private RedisTemplate<String, List<ProductResponseDto>> redisTemplateForProductList;

    @Autowired
    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient,
                                   RestTemplateBuilder restTemplateBuilder,
                                   RedisTemplate<String, Object> redisTemplate,
                                   RedisTemplate<String, List<ProductResponseDto>> redisTemplateForProductList){
        this.thirdPartyProductServiceClient = fakeStoreProductServiceClient;
        this.restTemplateBuilder = restTemplateBuilder;
        this.redisTemplate = redisTemplate;
        this.redisTemplateForProductList = redisTemplateForProductList;
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
        //See if the product is already store in cache.
        ProductResponseDto productResponseDto = (ProductResponseDto) this.redisTemplate.opsForHash().get("PRODUCTS", id);
        //If yes, return the same product.
        if(productResponseDto != null){
            return productResponseDto;
        }
        //Else, make appropriate API call and store the product in cache before returning.
        //First time response time : 1027ms.
        //Subsequent response time : 11ms
        //more than 90% improvement.
        productResponseDto = this.thirdPartyProductServiceClient.getProductById(id);
        this.redisTemplate.opsForHash().put("PRODUCTS", id, productResponseDto);
        return productResponseDto;
    }


    @Override
    public List<ProductResponseDto> getAllProducts() {
        //Check if all the products are already in cache
        List<ProductResponseDto> productResponseDtos = (List<ProductResponseDto>)this.redisTemplateForProductList.opsForHash().get("PRODUCTS", "ALL");
        //If yes, return from cache.
        if(productResponseDtos != null){
            return productResponseDtos;
        }
        //Else, make appropriate API call and store the product in cache before returning.
        //First time response time : 2026ms.
        //Subsequent response time : 20ms
        //Around 90% improvement.
        productResponseDtos = this.thirdPartyProductServiceClient.getAllProducts();
        this.redisTemplateForProductList.opsForHash().put("PRODUCTS", "ALL", productResponseDtos);
        return productResponseDtos;
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        return this.thirdPartyProductServiceClient.createProduct(productRequestDto);
    }

    @Override
    public boolean deleteProduct(Long id) {
        return this.thirdPartyProductServiceClient.deleteProduct(id);
    }

    @Override
    public void updateProduct(Long id, ProductRequestDto productRequestDto) throws ProductNotFoundException {
        this.thirdPartyProductServiceClient.updateProduct(id, productRequestDto);
    }
}
