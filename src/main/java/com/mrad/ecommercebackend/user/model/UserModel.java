package com.mrad.ecommercebackend.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mrad.ecommercebackend.address.Address;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserModel {

    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private String first_name;
    private String last_name;
    @JsonIgnore
    private List<Address> addresses = new ArrayList<>();
    private List<VerificationToken> verificationTokens = new ArrayList<>();
    private Boolean  emailVerified = false;
    public UserModel() {
    }

    public UserModel(String username, String email, String first_name, String last_name) {
        this.username = username;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }
}
