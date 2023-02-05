package com.mrad.ecommercebackend.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ProductDataAccessService implements ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Product> getAllProducts() {
        var sql = """
                SELECT *
                FROM
                product
                LIMIT 20;
                """;
        return jdbcTemplate.query(sql, new ProductRowMapper());
    }
}
