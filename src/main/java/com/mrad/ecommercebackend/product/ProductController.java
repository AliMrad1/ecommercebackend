package com.mrad.ecommercebackend.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
class ProductController {
    @Autowired
    private  ProductService service;
    @GetMapping("/all")
    public List<Product> allProducts() {
            return service.allProducts();
    }
}