package com.mrad.ecommercebackend.order.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mrad.ecommercebackend.product.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderQuantities {

    private Long id;
    private Product product;
    private Integer quantity;
    @JsonIgnore
    private Order order;
}
