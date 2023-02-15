package com.mrad.ecommercebackend.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mrad.ecommercebackend.user.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Address {

    private Long id;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String country;
    @JsonIgnore
    private UserModel user;

    public Address() {
    }

    public Address(String addressLine1, String addressLine2, String city, UserModel user) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.user = user;
    }
}
