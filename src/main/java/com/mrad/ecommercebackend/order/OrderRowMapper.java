package com.mrad.ecommercebackend.order;

import com.mrad.ecommercebackend.address.Address;
import com.mrad.ecommercebackend.order.model.Order;
import com.mrad.ecommercebackend.user.model.UserModel;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Order(
                rs.getLong("id"),
                new UserModel(
                        rs.getLong("user_id")
                ),
                new Address(
                        rs.getLong("address_id")
                )
        );
    }
}
