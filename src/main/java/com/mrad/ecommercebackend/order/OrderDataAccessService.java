package com.mrad.ecommercebackend.order;

import com.mrad.ecommercebackend.address.Address;
import com.mrad.ecommercebackend.interfaces.MapExtractData;
import com.mrad.ecommercebackend.order.model.Order;
import com.mrad.ecommercebackend.order.model.OrderQuantities;
import com.mrad.ecommercebackend.user.model.UserModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class OrderDataAccessService implements OrderRepository, MapExtractData<Order> {

    private final JdbcTemplate jdbcTemplate;

    public OrderDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Order> findByUser(UserModel user) {
        var sql = """
                SELECT * from public.getallordersbyuserid(
                	?
                )
                """;
        List<Map<String, Object>> ordersMap =  jdbcTemplate.queryForList(sql, user.getId());
        return extractDataToList(ordersMap);
    }

    @Override
    public void insertOrder(Long address_id, Long user_id) {
        var sql = """
                CALL public.insert_order(
                	?,
                	?
                )
                """;

        jdbcTemplate.update(sql, address_id.intValue(), user_id.intValue());
    }

    @Override
    public List<OrderQuantities> findOrderQuantitiesByProductId() {
        return null;
    }

    @Override
    public void insertOrderQuantities(Integer quantity, Integer order_id, Integer product_id) {

    }

    @Override
    public List<Order> extractDataToList(List<Map<String, Object>> mapList) {
        List<Order> orders = new ArrayList<>();
        for (Map<String, Object> map : mapList)
        {
            Order order = new Order(
                    (Long) map.get("order_id"),
                    new UserModel(
                            (Long) map.get("user_id"),
                            (String) map.get("first_name"),
                            (String) map.get("last_name")
                    ),
                    new Address(
                            (Long) map.get("address_id"),
                            (String) map.get("address_line1"),
                            (String) map.get("address_line2"),
                            (String) map.get("city"),
                            (String) map.get("country")
                    )
            );
            orders.add(order);
        }

        return orders;
    }
}
