package com.mrad.ecommercebackend.order;

import com.mrad.ecommercebackend.order.model.Order;
import com.mrad.ecommercebackend.order.model.OrderQuantities;
import com.mrad.ecommercebackend.user.model.UserModel;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository
public interface OrderRepository  {

    List<Order> findByUser(UserModel user);
    Optional<Order> getOneSingleOrderForASpecificUserId(Long order_id);
    void insertOrder(Long address_id, Long user_id);

}
