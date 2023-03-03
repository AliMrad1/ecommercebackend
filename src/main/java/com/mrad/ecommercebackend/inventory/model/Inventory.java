package com.mrad.ecommercebackend.inventory.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Inventory {

    private Long id;
    private Integer quantity;
    private Timestamp created_at;
    private Timestamp modified_at;


    public Inventory(){}
}
