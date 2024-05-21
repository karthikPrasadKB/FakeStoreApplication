package com.ECommerce.FakeStoreApplication.thirdParty.productService.fakeStore;

import com.ECommerce.FakeStoreApplication.dtos.FakeStoreProductDto;
import com.ECommerce.FakeStoreApplication.dtos.ProductRequestDto;
import com.ECommerce.FakeStoreApplication.dtos.ProductResponseDto;
import com.ECommerce.FakeStoreApplication.exceptions.ProductNotFoundException;
import com.ECommerce.FakeStoreApplication.thirdParty.productService.ThirdPartyProductServiceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductServiceClient implements ThirdPartyProductServiceClient {
    @Value("${fakestore.api.url}")
    private String fakeStoreApiUrl;
    @Value("${fakestore.api.paths.product}")
    private String fakeStoreProductPath;
    private String productBaseUrl;
    private String productSpecificUrl;
    private RestTemplate restTemplate;

    public FakeStoreProductServiceClient(@Value("${fakestore.api.url}") String fakeStoreApiUrl,
                                         @Value("${fakestore.api.paths.product}") String fakeStoreProductPath,
                                         RestTemplate restTemplate){
        this.fakeStoreApiUrl = fakeStoreApiUrl;
        this.fakeStoreProductPath = fakeStoreProductPath;
        this.productBaseUrl = this.fakeStoreApiUrl + this.fakeStoreProductPath;
        this.productSpecificUrl = productBaseUrl + "/{id}";
        this.restTemplate = restTemplate;
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
        ResponseEntity<FakeStoreProductDto> response = this.restTemplate.getForEntity(this.productSpecificUrl,
                                                                                      FakeStoreProductDto.class, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        if(fakeStoreProductDto == null){
            throw new ProductNotFoundException("Product not found");
        }
        return this.getProductDtoFromFakeStoreProductDto(fakeStoreProductDto);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        ResponseEntity<FakeStoreProductDto[]> response = this.restTemplate.getForEntity(this.productBaseUrl,
                                                                                        FakeStoreProductDto[].class);
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : response.getBody()){
            productResponseDtoList.add(this.getProductDtoFromFakeStoreProductDto(fakeStoreProductDto));
        }
        return productResponseDtoList;
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        ResponseEntity<FakeStoreProductDto> response = this.restTemplate.postForEntity(this.productBaseUrl,
                                                                                       productRequestDto,
                                                                                       FakeStoreProductDto.class);
        return this.getProductDtoFromFakeStoreProductDto(response.getBody());
    }


    @Override
    public boolean deleteProduct(Long id) {
        return true;
    }

    @Override
    public void updateProduct(Long id, ProductRequestDto productRequestDto) throws ProductNotFoundException {
        ProductResponseDto responseDto = this.getProductById(id);
        this.restTemplate.put(this.productSpecificUrl, productRequestDto, id, FakeStoreProductDto.class);
    }
}
