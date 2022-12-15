package com.mrad.ecommercebackend.inventory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mrad.ecommercebackend.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Inventory {

    @Id
    @SequenceGenerator(
            name = "inventor_id_generator",
            sequenceName = "inventor_id_generator",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "inventor_id_generator"
    )
    private Long id;
    @JsonIgnore
    @OneToOne(optional = false,orphanRemoval = true)
    @JoinColumn(name = "product_id", nullable = false, unique = true)
    private Product product;
    @Column(nullable = false)
    private Integer quantity;


}
