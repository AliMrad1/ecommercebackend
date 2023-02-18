package com.mrad.ecommercebackend.order.model;

import com.mrad.ecommercebackend.address.Address;
import com.mrad.ecommercebackend.user.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Order {

    private Long id;
    private UserModel user;
    private Address address;
    /** The quantities ordered. */
    private List<OrderQuantities> quantities = new ArrayList<>();

    public Order(long id, UserModel user, Address address) {
        this.id = id;
        this.user = user;
        this.address = address;
    }
}
