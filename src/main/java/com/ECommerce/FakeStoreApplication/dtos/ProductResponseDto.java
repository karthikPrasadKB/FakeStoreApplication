package com.ECommerce.FakeStoreApplication.dtos;

import com.ECommerce.FakeStoreApplication.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
//Generic Product Response DTO
public class ProductResponseDto implements Serializable {
    private long id;
    private String title;
    private String category;
    private float price;
    private String description;
    private String image;
}
