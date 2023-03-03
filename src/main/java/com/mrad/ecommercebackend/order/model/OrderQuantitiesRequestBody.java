package com.mrad.ecommercebackend.order.model;

public record OrderQuantitiesRequestBody(
        Long user_id,
        Integer quantity,
        Long order_id,
        Long product_id) {
}
