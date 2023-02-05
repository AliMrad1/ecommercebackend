package com.mrad.ecommercebackend.order.model;

import com.mrad.ecommercebackend.address.Address;
import com.mrad.ecommercebackend.user.model.UserModel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Order {

    private Long id;
    private UserModel user;
    private Address address;
    /** The quantities ordered. */
    private List<OrderQuantities> quantities = new ArrayList<>();
}
