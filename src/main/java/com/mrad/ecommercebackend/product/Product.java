package com.mrad.ecommercebackend.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mrad.ecommercebackend.inventory.model.Inventory;
import com.mrad.ecommercebackend.product.category.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {

    private Long id;
    private String productName;
    private String shortDescription;
    private String longDescription;
    private Double price;
    @JsonIgnore
    private Inventory inventory;
    private Category category;

    public Product(Long id, String productName, String shortDescription, String longDescription, Double price) {
        this.id = id;
        this.productName = productName;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.price = price;
    }

    public Product(Long productId, String productName) {
        this.id = productId;
        this.productName = productName;
    }

    public Product(Long productId) {
        this.id = productId;
    }
}