package com.mrad.ecommercebackend.order;

import com.mrad.ecommercebackend.address.Address;
import com.mrad.ecommercebackend.address.AddressService;
import com.mrad.ecommercebackend.exception.NullValueException;
import com.mrad.ecommercebackend.exception.ValueNotMatchedException;
import com.mrad.ecommercebackend.order.model.Order;
import com.mrad.ecommercebackend.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;
    @Autowired
    private AddressService addressService;
    public List<Order> getAllOrdersByUser(UserModel user){
        return repository.findByUser(user);
    }

    public void insertOrder(Long addressId,UserModel user){
        Address address = addressService.findAddressById(addressId);
        if(addressId == null || user == null){
            throw new NullValueException("Please don't enter a null value!!");
        }

        var isNotEqual = !Objects.equals(address.getUser().getId(), user.getId());
        if(isNotEqual){
            throw new ValueNotMatchedException("Please this address not for this specific user!");
        }
        repository.insertOrder(addressId, user.getId());
    }
}

