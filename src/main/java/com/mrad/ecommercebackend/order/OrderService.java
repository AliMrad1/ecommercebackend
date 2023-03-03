package com.mrad.ecommercebackend.order;

import com.mrad.ecommercebackend.address.Address;
import com.mrad.ecommercebackend.address.AddressService;
import com.mrad.ecommercebackend.exception.NullValueException;
import com.mrad.ecommercebackend.exception.ValueNotMatchedException;
import com.mrad.ecommercebackend.interfaces.CheckPermission;
import com.mrad.ecommercebackend.order.model.Order;
import com.mrad.ecommercebackend.order.model.OrderQuantitiesRequestBody;
import com.mrad.ecommercebackend.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired OrderQuantitiesRepository orderQuantitiesRepository;
    @Autowired
    private AddressService addressService;
    public List<Order> getAllOrdersByUser(UserModel user){
        return orderRepository.findByUser(user);
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
        orderRepository.insertOrder(addressId, user.getId());
    }

    public boolean insertOrderQuantities(UserModel userAuthenticated, OrderQuantitiesRequestBody body){

        Optional<Order> order = orderRepository.getOneSingleOrderForASpecificUserId(body.order_id());

        if(order.isPresent()){
            if(CheckPermission.userHasPermission(userAuthenticated,order.get().getUser().getId())){
                if(body.quantity() == null
                        || body.order_id() == null
                        || body.product_id() == null)
                {
                    throw new NullValueException("Please don't enter a null value!!");
                }

                orderQuantitiesRepository.insertOrderQuantities(
                        body.quantity(),
                        body.order_id(),
                        body.product_id()
                );

                return true;
            }

        }
        return false;
    }
}

