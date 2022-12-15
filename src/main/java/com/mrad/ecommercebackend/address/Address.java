package com.mrad.ecommercebackend.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mrad.ecommercebackend.user.model.UserModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {

    @Id
    @SequenceGenerator(
            name = "address_id_generator",
            sequenceName = "address_id_generator",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "address_id_generator"
    )
    private Long id;
    @Column(nullable = false)
    private String addressLine1;
    private String addressLine2;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String country;
    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
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
