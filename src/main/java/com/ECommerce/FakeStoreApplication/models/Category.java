package com.ECommerce.FakeStoreApplication.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "category")
public class Category extends BaseModel{
    private String categoryName;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product> productList;
}
