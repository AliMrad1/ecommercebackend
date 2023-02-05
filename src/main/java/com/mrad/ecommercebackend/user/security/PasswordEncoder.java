package com.mrad.ecommercebackend.user.security;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoder {

//    @Value("${encryption.salt.rounds}")
    private int saltRounds;
    private String salt;

    public PasswordEncoder() {
        saltRounds= 10;

        postConstruct();
    }

    public void postConstruct(){
        System.out.println("fired!!");
        salt = BCrypt.gensalt(saltRounds);
    }

    public String encryptPassword(String password){
        return BCrypt.hashpw(password, salt);
    }

    public boolean verifyPassword(String password, String hash){
        return BCrypt.checkpw(password, hash);
    }
}
