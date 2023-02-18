package com.mrad.ecommercebackend.order;

import com.mrad.ecommercebackend.order.model.Order;
import com.mrad.ecommercebackend.order.model.OrderQuantities;
import com.mrad.ecommercebackend.user.model.UserModel;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface OrderRepository  {

    List<Order> findByUser(UserModel user);
    void insertOrder(Long address_id, Long user_id);
    List<OrderQuantities> findOrderQuantitiesByProductId();
    void insertOrderQuantities(Integer quantity, Integer order_id, Integer product_id);
}
