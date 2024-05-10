package com.ECommerce.FakeStoreApplication.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
    private String title;
    private String category;
    private float price;
    private String description;
    private String image;
}
