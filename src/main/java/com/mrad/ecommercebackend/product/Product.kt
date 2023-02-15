package com.mrad.ecommercebackend.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mrad.ecommercebackend.inventory.Inventory;
import lombok.Getter;
import lombok.Setter;

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

    public Product() {
    }

    public Product(Long id, String productName, String shortDescription, String longDescription, Double price) {
        this.id = id;
        this.productName = productName;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.price = price;
    }
}
