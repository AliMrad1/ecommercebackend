package com.mrad.ecommercebackend.product.category;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
@ToString
public class ProductCategory {

    private Long id;
    private String name;
    private String description;
    private String createdAt;
    private String modifiedAt;
    private String deletedAt;
}
