package com.mrad.ecommercebackend.product;

import com.mrad.ecommercebackend.inventory.Inventory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @SequenceGenerator(
            name = "product_id_generator",
            sequenceName = "product_id_generator",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_id_generator"
    )
    private Long id;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private String shortDescription;
    @Column(nullable = false)
    private String longDescription;
    @Column(nullable = false)
    private Double price;
    @OneToOne(mappedBy = "product", cascade = CascadeType.REMOVE,optional = false,orphanRemoval = true)
    private Inventory inventory;

    public Product() {
    }
}
