package com.mrad.ecommercebackend.inventory.model;

import com.mrad.ecommercebackend.product.Product;

public record InventoryRequestBody(Product product,Integer quantity) {

}
