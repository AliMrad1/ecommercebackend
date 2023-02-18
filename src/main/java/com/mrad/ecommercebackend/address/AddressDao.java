package com.mrad.ecommercebackend.address;

import com.mrad.ecommercebackend.user.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface AddressDao {
    int insertAddress(Address address);
    Optional<Address> findByCity(String city);
    Optional<Address> findByCountry(String country);
    Optional<Address> findAddressById(Long addressId);
    int updateAddressById(Address addressId);
    List<Address> addresses(UserModel user);
    void deleteAddressByUser(UserModel user);
}