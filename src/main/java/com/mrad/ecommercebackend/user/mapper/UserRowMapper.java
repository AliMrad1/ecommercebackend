package com.mrad.ecommercebackend.user.mapper;

import com.mrad.ecommercebackend.user.model.UserModel;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRowMapper implements RowMapper<UserModel> {

    @Override
    public UserModel mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new UserModel(
                    resultSet.getLong("id"),
                    resultSet.getString("username"),
                    resultSet.getString("email"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("password"),
                    List.of(),
                    List.of(),
                    resultSet.getBoolean("email_verified")
        );
    }
}
