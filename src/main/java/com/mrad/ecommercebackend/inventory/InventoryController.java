package com.mrad.ecommercebackend.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mrad.ecommercebackend.exception.AlreadyExistException;
import com.mrad.ecommercebackend.inventory.model.InventoryRequestBody;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/new")
    public ResponseEntity<String> addNewProductToInventory(@RequestBody InventoryRequestBody body) {
        try{
            inventoryService.addNewProductInInventory(body);
            return ResponseEntity.ok("product adedd to inventory successfully!");

        }catch(AlreadyExistException e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
