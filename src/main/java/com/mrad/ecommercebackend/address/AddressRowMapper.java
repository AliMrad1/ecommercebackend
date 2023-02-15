package com.mrad.ecommercebackend.address;

import com.mrad.ecommercebackend.user.model.UserModel;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressRowMapper implements ResultSetExtractor<Address> {
    @Override
    public Address extractData(ResultSet rs) throws SQLException, DataAccessException {
        if (!rs.next()) {
            throw new SQLException("Wrong !!");
        }

        return new Address(
                rs.getLong("address_id"),
                rs.getString("address_line1"),
                rs.getString("address_line2"),
                rs.getString("city"),
                rs.getString("country"),
                new UserModel(
                        rs.getLong("us_id"),
                        rs.getString("username"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getBoolean("email_verified")
                )
        );

    }
}
