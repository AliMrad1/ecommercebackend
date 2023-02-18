package com.mrad.ecommercebackend.order;

import com.mrad.ecommercebackend.address.Address;
import com.mrad.ecommercebackend.order.model.Order;
import com.mrad.ecommercebackend.user.model.UserModel;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements ResultSetExtractor<Order> {
    @Override
    public Order extractData(ResultSet rs) throws SQLException, DataAccessException {
        return new Order(
                rs.getLong("id"),
                new UserModel(
                        rs.getLong("user_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                ),
                new Address(
                        rs.getLong("address_id"),
                        rs.getString("address_line1"),
                        rs.getString("address_line2"),
                        rs.getString("city"),
                        rs.getString("country")
                )
        );
    }
}
