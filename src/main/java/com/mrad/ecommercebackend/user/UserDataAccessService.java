package com.mrad.ecommercebackend.user;

import com.mrad.ecommercebackend.user.model.UserModel;
import com.mrad.ecommercebackend.user.model.VerificationToken;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDataAccessService implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<UserModel> selectUsers() {
        var sql = """
                SELECT id, username,first_name, last_name, email,email_verified
                FROM
                user_model;
                """;
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    @Override
    public int insertUser(UserModel user) {
        var sql = """
                INSERT INTO user_model (username,first_name, last_name, email,password)
                VALUES
                (?,?,?,?,?);
                """;
        return jdbcTemplate.update(sql,
                user.getUsername(),
                user.getFirst_name(),
                user.getLast_name(),
                user.getEmail(),
                user.getPassword()
                );
    }

    @Override
    public int deleteUser(int id) {
        var sql = """
                DELETE
                FROM user_model
                WHERE id = ?;
                """;
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<UserModel> findByUsername(String username) {
        var sql = """
                SELECT u.id,
                       u.username,
                       u.email,
                       u.first_name,
                       u.last_name,
                       u.password,
                       u.email_verified
                FROM user_model u
                WHERE u.username = ?;
                """;
        return jdbcTemplate.query(sql, new UserRowMapper(), username)
                .stream()
                .findFirst();
    }

    @Override
    public Optional<UserModel> findByEmail(String email) {
        var sql = """
                SELECT *
                FROM user_model
                WHERE email = ?;
                """;
        return jdbcTemplate.query(sql, new UserRowMapper(), email)
                .stream()
                .findFirst();
    }

    @Override
    public int update_verifiedUser(UserModel userModel) {
        var sql = """
                UPDATE  user_model
                SET email_verified=true
                WHERE id=?
                """;
        return jdbcTemplate.update(sql,
                userModel.getId()
        );
    }

    @Override
    public Optional<UserModel> findById(Long id) {
        var sql = """
                SELECT *
                FROM user_model
                WHERE id=?
                """;
        return jdbcTemplate.query(sql, new UserRowMapper(), id)
                .stream()
                .findFirst();
    }



}
