package com.mrad.ecommercebackend.address;

import com.mrad.ecommercebackend.product.ProductRepository;
import com.mrad.ecommercebackend.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressDao addressDao;

    public List<Address> getAllAddressesForSpecificUser(UserModel user){
        return addressDao.addresses(user);
    }

    public void addAddress(Address address) {
        addressDao.insertAddress(address);
    }

    public Address findAddressById(Long addressId) {
        Optional<Address> opOriginalAddress = addressDao.findAddressById(addressId);
        return opOriginalAddress.orElse(null);

    }

    public int updateAddressById(Address address) {
        return addressDao.updateAddressById(address);
    }
}
