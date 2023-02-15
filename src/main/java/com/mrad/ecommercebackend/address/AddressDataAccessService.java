package com.mrad.ecommercebackend.address;

import com.mrad.ecommercebackend.user.model.UserModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class AddressDataAccessService implements AddressDao {

    private final JdbcTemplate jdbcTemplate;

    public AddressDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertAddress(Address address) {

        var sql = """
                INSERT INTO address (id, address_line1, address_line2,city, country,user_id)
                VALUES
                (nextval('address_id_seq'),?,?,?,?,?);
                """;

        return jdbcTemplate.update(
                sql,
                address.getAddressLine1(),
                address.getAddressLine2(),
                address.getCity(),
                address.getCountry(),
                address.getUser().getId()
        );
    }

    @Override
    public Optional<Address> findByCity(String city) {
        var sql = """
                SELECT d.address_lin1,
                       d.address_lin2,
                       d.city,
                       d.country
                FROM address d
                WHERE d.city = ?
                """;

        return Optional
                .ofNullable(jdbcTemplate.query(sql, new AddressRowMapper(), city))
                .stream().findFirst();
    }

    @Override
    public Optional<Address> findByCountry(String country) {
        var sql = """
               SELECT d.address_lin1,
                       d.address_lin2,
                       d.city,
                       d.country
                FROM address d
                WHERE d.country = ?
                """;

        return Optional
                .ofNullable(jdbcTemplate.query(sql, new AddressRowMapper(), country))
                .stream().findFirst();
    }

    @Override
    public Optional<Address> findAddressById(Long addressId) {
        var sql = """
               SELECT d.id as address_id,
                    d.address_line1,
                    d.address_line2,
                    d.city,
                    d.country,
                    u.id as us_id,
                    u.username,
                    u.email,
                    u.first_name,
                    u.last_name,
                    u.email_verified
                FROM address d
                INNER JOIN user_model u ON u.id = d.user_id
                WHERE d.id = ?;
                """;

        return Optional
                .ofNullable(jdbcTemplate.query(sql, new AddressRowMapper(), addressId))
                .stream().findFirst();
    }

    @Override
    public int updateAddressById(Address address) {
        var sql = """
                UPDATE address
                SET address_line1= ?,
                    address_line2= ?,
                    city= ?,
                    country = ?
                WHERE id = ?;
                """;

        return jdbcTemplate.update(
                sql,
                address.getAddressLine1(),
                address.getAddressLine2(),
                address.getCity(),
                address.getCountry(),
                address.getId()
        );
    }

    @Override
    public List<Address> addresses(UserModel user) {

        //call procedure instead of using the whole query in your code
        var sql = """
               SELECT d.id as address_id,
                    d.address_line1,
                    d.address_line2,
                    d.city,
                    d.country,
                    u.id as us_id,
                    u.username,
                    u.email,
                    u.first_name,
                    u.last_name,
                    u.email_verified
                FROM address d
                INNER JOIN user_model u ON u.id = d.user_id
                WHERE d.user_id = ?;
                """;

        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, user.getId());

        List<Address> addresses = new ArrayList<>();

        for (Map<String, Object> map : list)
        {
            Address address = new Address(
                (Long) map.get("address_id"),
                (String) map.get("address_line1"),
                (String) map.get("address_line2"),
                (String) map.get("city"),
                (String) map.get("country"),
                new UserModel(
                        (Long) map.get("us_id"),
                        (String) map.get("username"),
                        (String) map.get("first_name"),
                        (String) map.get("last_name"),
                        (String) map.get("email"),
                        (Boolean) map.get("email_verified")
                )
        );


            addresses.add(address);
        }

        return addresses;
    }

    @Override
    public void deleteAddressByUser(UserModel user) {
        var sql = """
                DELETE
                FROM address
                WHERE user_id = ?;
                """;

        jdbcTemplate.update(sql, user.getId());
    }
}
