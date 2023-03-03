package com.mrad.ecommercebackend.order;

import com.mrad.ecommercebackend.order.model.OrderQuantities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderQuantitiesDataAccessService implements OrderQuantitiesRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<OrderQuantities> findOrderQuantitiesByProductId() {
        return  null;
    }

    @Override
    public void insertOrderQuantities(Integer quantity, Long order_id, Long product_id) {
        var sql = """
                CALL insert_order_quatities(
                    ?,
                    ?,
                    ?
                );
                """;

        jdbcTemplate.update(sql, quantity, order_id, product_id);
    }
}
