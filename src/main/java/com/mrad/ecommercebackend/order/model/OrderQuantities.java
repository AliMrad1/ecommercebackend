package com.mrad.ecommercebackend.order.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mrad.ecommercebackend.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderQuantities {

    @Id
    @SequenceGenerator(
            name = "orderQuantities_id_generator",
            sequenceName = "orderQuantities_id_generator",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "orderQuantities_id_generator"
    )
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    @Column(nullable = false)
    private Integer quantity;
    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
