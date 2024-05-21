package com.ECommerce.FakeStoreApplication.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//DTO associated to fakeStore.com
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private float price;
    private String description;
    private String category;
    private String image;
}
