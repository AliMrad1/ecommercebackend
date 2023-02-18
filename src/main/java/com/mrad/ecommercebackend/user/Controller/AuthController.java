package com.mrad.ecommercebackend.user.Controller;

import com.mrad.ecommercebackend.user.service.UserService;
import com.mrad.ecommercebackend.user.exception.UserExistException;
import com.mrad.ecommercebackend.user.exception.UserNotVerifiedException;
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
public class AuthController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegistrationBody body) {
        UserModel user = null;
        try {
            user = service.register(body);
        } catch (UserExistException e) {
            System.out.println("Something wrong !!!!!!!!!!!" + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok(user.toString());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginBody body) {
        String jwt = null;
        try {
            jwt = service.loginUser(body);
        } catch (UserNotVerifiedException e) {
            String s = "User Not Found";
            if (e.isNewEmailSend()) {
                s += ", Email RESENT";
            }
            LoginResponse response = new LoginResponse("", false, s);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
        if (jwt == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        LoginResponse response = new LoginResponse(jwt, true, "No Failure");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/confirm")
    public ResponseEntity<String> verifyEmail(@RequestParam String token) {
        if (service.verifyUser(token)) {
            return ResponseEntity.ok("Verified");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @GetMapping("/me")
    public UserModel getUser(@AuthenticationPrincipal UserModel userAuthenticated) {
        return userAuthenticated;
    }

}