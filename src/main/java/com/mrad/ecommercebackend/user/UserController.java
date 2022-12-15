package com.mrad.ecommercebackend.user;

import com.mrad.ecommercebackend.user.exception.UserExistException;
import com.mrad.ecommercebackend.user.model.LoginBody;
import com.mrad.ecommercebackend.user.model.LoginResponse;
import com.mrad.ecommercebackend.user.model.RegistrationBody;
import com.mrad.ecommercebackend.user.model.UserModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegistrationBody body){
        UserModel user  = null;
        try {
            user = service.register(body);
        } catch (UserExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok("valid");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginBody body){
        String jwt = service.loginUser(body);
        if(jwt == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        LoginResponse response = new LoginResponse(jwt);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public UserModel getUser(@AuthenticationPrincipal UserModel userAuthenticated){
        return userAuthenticated;
    }
}
