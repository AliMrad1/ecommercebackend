package com.mrad.ecommercebackend.inventory;

import com.mrad.ecommercebackend.interfaces.MapExtractData;
import com.mrad.ecommercebackend.inventory.model.Inventory;
import com.mrad.ecommercebackend.product.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InventoryRowMapper implements MapExtractData<Inventory> {

    @Override
    public List<Inventory> extractDataToList(List<Map<String, Object>> mapList) {

        List<Inventory> inventories = new ArrayList<>();
        for (Map<String, Object> map : mapList){
            Inventory inventory = new Inventory(
                    (Long) map.get("inventory_id"),
                    new Product(
                            (Long) map.get("product_id"),
                            (String) map.get("product_name")
                    ),
                    (Integer) map.get("quantity")
            );

            inventories.add(inventory);
        }
            return inventories;
    }

}
