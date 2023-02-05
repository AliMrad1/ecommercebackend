package com.mrad.ecommercebackend.user;

import com.mrad.ecommercebackend.user.model.UserModel;
import com.mrad.ecommercebackend.user.model.VerificationToken;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class VerificationTokenDataAccessService implements VerificationTokenDao{

    private final JdbcTemplate jdbcTemplate;
    public VerificationTokenDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public int insertToken(VerificationToken token) {
        var sql = """
                INSERT INTO verification_token (token, created_time_stamp, user_id)
                VALUES
                (?,?,?);
                """;
        return jdbcTemplate.update(sql,
                token.getToken(),
                token.getCreatedTimeStamp(),
                token.getUser().getId()
        );
    }

    private VerificationToken v;
    public Optional<VerificationToken> findByToken(String token) {
        var sql = """
                SELECT v.id as ver_id,
                        v.token,
                        v.created_time_stamp,
                        v.user_id,
                        u.id as us_id,
                        u.username,
                        u.email,
                        u.first_name,
                        u.last_name,
                        u.email_verified
                FROM verification_token v
                INNER JOIN user_model u ON u.id = v.user_id
                WHERE v.token = ?;
                """;

        return Optional.ofNullable(jdbcTemplate.query(sql, rs -> {

            if (!rs.next()) {
                throw new SQLException("Wrong !!");
            }

            v = new VerificationToken(
                    rs.getLong("ver_id"),
                    rs.getString("token"),
                    rs.getTimestamp("created_time_stamp"),
                    new UserModel(
                            rs.getLong("us_id"),
                            rs.getString("username"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("email"),
                            rs.getBoolean("email_verified")
                    )
            );
            return v;
        }, token))
                .stream().findFirst();

    }

    public void deleteByUser(UserModel user) {
        var sql = """
                delete from verification_token
                where
                user_id = ?;
                """;
        jdbcTemplate.update(sql, user.getId());
    }
}
