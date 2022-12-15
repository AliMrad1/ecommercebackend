package com.mrad.ecommercebackend.order.model;

import com.mrad.ecommercebackend.address.Address;
import com.mrad.ecommercebackend.user.model.UserModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "order_p")
@Getter
@Setter
public class Order {

    @Id
    @SequenceGenerator(
            name = "order_id_generator",
            sequenceName = "order_id_generator",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_id_generator"
    )
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;
    @ManyToOne(optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
    /** The quantities ordered. */
    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<OrderQuantities> quantities = new ArrayList<>();
}
