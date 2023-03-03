package com.mrad.ecommercebackend.inventory;

import com.mrad.ecommercebackend.inventory.model.Inventory;
import com.mrad.ecommercebackend.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InventoryDataAccessService implements InventoryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void increaseQuantityOfASpecificProduct(Integer quantity,Long productId) {
        var sql = """
                call increaseQuantityOfProduct(?,?);
                """;
        jdbcTemplate.update(sql, quantity, productId);
    }

    @Override
    public void addNewProductInInventory(Product product) {
        var sql = """
                call addNewProductToInventory(?,?,?,?,?,?);
                """;
                
        jdbcTemplate.update(sql,
                product.getInventory().getQuantity(),
                product.getLongDescription(),
                product.getPrice(),
                product.getProductName(),
                product.getShortDescription(),
                product.getCategory().getId()
        );
    }

    @Override
    public List<Inventory> getAllProductsWithQuantity() {

        var sql = """
                SELECT *
                FROM
                getAllProductWithQuantity();
                """;

        List<Map<String, Object>> ordersMap =  jdbcTemplate.queryForList(sql);
        return new InventoryRowMapper().extractDataToList(ordersMap);
    }

    @Override
    public Optional<Inventory> getProductAndQuantityInInventory(Long productID) {

        var sql = """
                select * from getproductwithquantitybyid(?);
                """;

        return Optional.ofNullable(jdbcTemplate.query(sql,
                rs -> {
                        if(rs.next()){
                                return new Inventory(
                                        rs.getLong("inventory_id"),
                                        rs.getInt("quantity"),
                                        rs.getTimestamp("create_at"),
                                        rs.getTimestamp("modified_at")
                                );
                        }
                        
                        return null;
                },
                productID));
    }
}
