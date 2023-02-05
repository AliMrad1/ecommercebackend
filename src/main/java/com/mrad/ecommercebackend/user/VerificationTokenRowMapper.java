package com.mrad.ecommercebackend.user;

import com.mrad.ecommercebackend.user.model.UserModel;
import com.mrad.ecommercebackend.user.model.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class VerificationTokenRowMapper {

}
