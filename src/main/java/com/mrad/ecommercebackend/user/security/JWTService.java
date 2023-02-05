package com.mrad.ecommercebackend.user.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mrad.ecommercebackend.user.model.UserModel;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    @Value("${jwt.algorithm.key}")
    private String algorithmKey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiredInSeconds}")
    private int expiredInSeconds;
    private Algorithm algorithm;

    private static final String USERNAME_KEY= "username";
    private static final String EMAIL_KEY= "email";

    @PostConstruct
    public void postConstruct(){
        algorithm = Algorithm.HMAC256(algorithmKey);
    }

    public String generateJWT(UserModel user){
        return JWT.create()
                  .withClaim(USERNAME_KEY, user.getUsername())
                  .withExpiresAt(new Date(System.currentTimeMillis() + (1000L * expiredInSeconds)))
                  .withIssuer(issuer)
                .sign(algorithm);
    }

    public String generateVerificationJWT(UserModel user){
        return JWT.create()
        .withClaim(EMAIL_KEY, user.getEmail())
        .withExpiresAt(new Date(System.currentTimeMillis() + (1000L * expiredInSeconds)))
        .withIssuer(issuer)
        .sign(algorithm);
    }

    public String getUsername(String token){
        return JWT.decode(token)
                .getClaim(USERNAME_KEY).asString();
    }

}
