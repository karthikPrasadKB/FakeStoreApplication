package com.ECommerce.FakeStoreApplication.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "products")
public class Product extends BaseModel{
    private String name;
    private String description;
    @ManyToOne
    private Category category;
    private float price;
    private String imageLink;
}
