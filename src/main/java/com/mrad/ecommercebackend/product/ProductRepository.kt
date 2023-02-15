package com.mrad.ecommercebackend.product

interface ProductRepository {
    fun allProducts():List<Product>
}
