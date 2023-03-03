package com.mrad.ecommercebackend.inventory;

import java.util.List;
import java.util.Optional;

import com.mrad.ecommercebackend.inventory.model.Inventory;
import com.mrad.ecommercebackend.product.Product;

public interface InventoryRepository {

    void increaseQuantityOfASpecificProduct(Integer quantity,Long productId);//increase quantity
    void addNewProductInInventory(Product product);
    List<Inventory> getAllProductsWithQuantity();
    Optional<Inventory> getProductAndQuantityInInventory(Long productID);

}
