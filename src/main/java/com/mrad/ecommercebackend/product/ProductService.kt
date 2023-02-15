package com.mrad.ecommercebackend.product

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService @Autowired constructor(private val repository: ProductRepository) {
    fun allProducts(): List<Product>{
        return repository.allProducts()
    }

}