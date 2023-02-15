package com.mrad.ecommercebackend.product

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product")
class ProductController @Autowired constructor(private val service: ProductService) {

    @GetMapping("/all")
    fun allProducts(): List<Product> {
            return service.allProducts()
    }
}