package com.ECommerce.FakeStoreApplication.dtos;

import com.ECommerce.FakeStoreApplication.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//Generic Product Response DTO
public class ProductResponseDto {
    private long id;
    private String title;
    private String category;
    private float price;
    private String description;
    private String image;
}
