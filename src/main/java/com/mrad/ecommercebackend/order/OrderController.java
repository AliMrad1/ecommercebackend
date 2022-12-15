package com.mrad.ecommercebackend.order;

import com.mrad.ecommercebackend.order.model.Order;
import com.mrad.ecommercebackend.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping()
    public List<Order> getOrdersByUser(@AuthenticationPrincipal UserModel user){
        return service.getAllOrdersByUser(user);
    }
}
