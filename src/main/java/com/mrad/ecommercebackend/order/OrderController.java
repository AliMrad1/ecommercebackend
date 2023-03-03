package com.mrad.ecommercebackend.order;

import com.mrad.ecommercebackend.interfaces.CheckPermission;
import com.mrad.ecommercebackend.order.model.Order;
import com.mrad.ecommercebackend.order.model.OrderBody;
import com.mrad.ecommercebackend.order.model.OrderQuantitiesRequestBody;
import com.mrad.ecommercebackend.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    private OrderService service;
    @GetMapping("/{user_id}")
    public ResponseEntity<List<Order>> getOrdersByUser(
            @AuthenticationPrincipal UserModel user,
            @PathVariable("user_id") Long user_id)
    {
        if (!CheckPermission.userHasPermission(user, user_id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(service.getAllOrdersByUser(user));
    }

    @PostMapping("/new")
    public ResponseEntity<String> addOrder(@RequestBody OrderBody orderBody,
                                           @AuthenticationPrincipal UserModel user){
        if(CheckPermission.userHasPermission(user, orderBody.user_id())){
            service.insertOrder(orderBody.address_id(), user);
            return ResponseEntity.ok("Order Added!");
        }

        return ResponseEntity.badRequest().build();
    }
    @PostMapping("/oq/new")
    public ResponseEntity<String> addOrderQuantities(
            @RequestBody OrderQuantitiesRequestBody orderQuantitiesRequestBody,
            @AuthenticationPrincipal UserModel user)
    {
            boolean isPermitted = service.insertOrderQuantities(user, orderQuantitiesRequestBody);
            if(isPermitted){
                return ResponseEntity.ok("Order Quantity Added!");
            }

            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
