package com.mrad.ecommercebackend.user.Controller;

import com.mrad.ecommercebackend.address.Address;
import com.mrad.ecommercebackend.address.AddressService;
import com.mrad.ecommercebackend.interfaces.CheckPermission;
import com.mrad.ecommercebackend.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/{userId}/address")
    public ResponseEntity<List<Address>> getAllAddressesByUser(
            @AuthenticationPrincipal UserModel userAuthenticated,
            @PathVariable(required = true, name = "userId") Long id){

        if (!CheckPermission.userHasPermission(userAuthenticated, id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(addressService.getAllAddressesForSpecificUser(userAuthenticated));
    }

    @PutMapping("/{userId}/address")
    public ResponseEntity<Address> putAddress(
            @AuthenticationPrincipal UserModel user, @PathVariable("userId") Long userId,
            @RequestBody Address address) {
        if (!CheckPermission.userHasPermission(user, userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        UserModel refUser = new UserModel();
        refUser.setId(userId);
        address.setUser(refUser);
        addressService.addAddress(address);
        return ResponseEntity.ok(null);
    }

    @PatchMapping("/{userId}/address/{addressId}")
    public ResponseEntity<String> patchAddress(
            @AuthenticationPrincipal UserModel user, @PathVariable("userId") Long userId,
            @PathVariable("addressId") Long addressId, @RequestBody Address address) {
        if (!CheckPermission.userHasPermission(user, userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (Objects.equals(address.getId(), addressId)) {
            Address opOriginalAddress = addressService.findAddressById(addressId);
            if (opOriginalAddress != null) {
                UserModel originalUser = opOriginalAddress.getUser();
                if (Objects.equals(originalUser.getId(), userId)) {
                    address.setUser(originalUser);
                    addressService.updateAddressById(address);
                    return ResponseEntity.ok("The Address has been updated!");
                }
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
