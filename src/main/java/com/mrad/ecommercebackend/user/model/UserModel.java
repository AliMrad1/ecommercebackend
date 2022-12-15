package com.mrad.ecommercebackend.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mrad.ecommercebackend.address.Address;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class UserModel {

    @Id
    @SequenceGenerator(
            name = "user_id_sequence",
            sequenceName = "user_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_id_sequence"
    )
    private Long id;
    @Column(nullable = false,unique = true)
    private String username;
    @JsonIgnore
    @Column(nullable = false,length = 1000)
    private String password;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String first_name;

    @Column(nullable = false)
    private String last_name;
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id desc")
    private List<VerificationToken> verificationTokens = new ArrayList<>();

    @Column(nullable = false)
    private Boolean  emailVerified = false;

    public UserModel() {
    }

    public UserModel(String username, String email, String first_name, String last_name) {
        this.username = username;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
    }

}
