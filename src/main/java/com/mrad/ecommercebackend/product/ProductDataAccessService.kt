package com.mrad.ecommercebackend.product

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class ProductDataAccessService
    @Autowired constructor(private val jdbcTemplate: JdbcTemplate) : ProductRepository {

    override fun allProducts(): List<Product> {
        val sql = """
                SELECT *
                FROM
                product
                LIMIT 20;
                """.trimIndent()

        return jdbcTemplate.query(sql, ProductRowMapper())
    }
}