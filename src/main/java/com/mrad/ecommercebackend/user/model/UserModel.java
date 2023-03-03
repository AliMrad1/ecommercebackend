package com.mrad.ecommercebackend.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mrad.ecommercebackend.address.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    @JsonIgnore
    private List<VerificationToken> verificationTokens = new ArrayList<>();
    private Boolean  emailVerified = false;
    public UserModel(long id, String username, String firstName, String lastName, String email, boolean emailVerified) {
        this.id = id;
        this.username = username;
        this.first_name = firstName;
        this.last_name = lastName;
        this.email = email;
        this.emailVerified = emailVerified;
    }

    public UserModel(){

    }

    public UserModel(String username, String email, String first_name, String last_name) {
        this.username = username;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public UserModel(String username, String email, String first_name, String last_name,String password) {
        this.username = username;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password= password;
    }

    public UserModel(Long id,String username, String email, String first_name, String last_name,String password,
                     List<VerificationToken> verificationTokens,List<Address> addresses) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password= password;
        this.verificationTokens = verificationTokens;
        this.addresses = addresses;
    }

    public UserModel(long id, String username, String email, String firstName, String lastName, String password, List<Address> of, List<VerificationToken> of1, boolean emailVerified) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.first_name = firstName;
        this.last_name = lastName;
        this.password = password;
        this.addresses = of;
        this.verificationTokens = of1;
        this.emailVerified = emailVerified;
    }

    public UserModel(Long id, String firstName, String lastName) {
        this.id = id;
        this.first_name = firstName;
        this.last_name = lastName;
    }

    public UserModel(Long userId) {
        this.id = userId;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", addresses=" + addresses +
                ", verificationTokens=" + verificationTokens +
                ", emailVerified=" + emailVerified +
                '}';
    }
}
