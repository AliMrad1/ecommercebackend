package com.mrad.ecommercebackend.product;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Product(
                rs.getLong("id"),
                rs.getString("product_name"),
                rs.getString("short_description"),
                rs.getString("long_description"),
                rs.getDouble("price")
        );
    }
}