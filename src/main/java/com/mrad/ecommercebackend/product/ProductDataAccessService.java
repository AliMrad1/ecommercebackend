package com.mrad.ecommercebackend.product;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class ProductDataAccessService implements ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @NotNull
    @Override
    public List<Product> allProducts() {
        var sql = """
                SELECT *
                FROM
                product
                LIMIT 20;
                """;

        return jdbcTemplate.query(sql, new ProductRowMapper());
    }
}