package com.mrad.ecommercebackend.inventory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mrad.ecommercebackend.product.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Inventory {

    private Long id;
    @JsonIgnore
    private Product product;
    private Integer quantity;
}
