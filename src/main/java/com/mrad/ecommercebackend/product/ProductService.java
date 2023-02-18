package com.mrad.ecommercebackend.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ProductService {

    @Autowired
    private ProductRepository repository;
    public List<Product> allProducts() {
        return repository.allProducts();
    }

}