package com.mrad.ecommercebackend.product

import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import java.sql.SQLException

class ProductRowMapper : RowMapper<Product> {
    @Throws(SQLException::class)
    override fun mapRow(rs: ResultSet, rowNum: Int): Product {
        return Product(
                rs.getLong("id"),
                rs.getString("product_name"),
                rs.getString("short_description"),
                rs.getString("long_description"),
                rs.getDouble("price")
        )
    }
}