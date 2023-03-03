package com.mrad.ecommercebackend.order;

import com.mrad.ecommercebackend.order.model.OrderQuantities;

import java.util.List;

public interface OrderQuantitiesRepository {
    List<OrderQuantities> findOrderQuantitiesByProductId();
    void insertOrderQuantities(Integer quantity, Long order_id, Long product_id);
}
