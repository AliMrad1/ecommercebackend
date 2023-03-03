package com.mrad.ecommercebackend.inventory;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrad.ecommercebackend.exception.AlreadyExistException;
import com.mrad.ecommercebackend.inventory.model.Inventory;
import com.mrad.ecommercebackend.inventory.model.InventoryRequestBody;
import com.mrad.ecommercebackend.product.Product;
import com.mrad.ecommercebackend.product.category.Category;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public void addNewProductInInventory(InventoryRequestBody body) throws AlreadyExistException, IllegalArgumentException {
        // in database will check if product already exist or not
        if(body.quantity() < 0) {
            throw new IllegalArgumentException("Only Positive Numbers & no Letters Please!"); 
        }
       
      //  Optional<Category> productExist = inventoryRepository.getProductAndQuantityInInventory(body.product().getCategory().getId());

        // if(productExist.isPresent()){
        //     var message = "product already exist in inventory, you want to increase the quantity of this product!!";
        //     throw new AlreadyExistException(message);
        // }

        Product product = new Product();
        product.setInventory(body.product().getInventory());
        product.setLongDescription(body.product().getLongDescription());
        product.setProductName(body.product().getProductName());
        product.setShortDescription(body.product().getShortDescription());
        product.setPrice(body.product().getPrice());

        inventoryRepository.addNewProductInInventory((product));
    }
}
