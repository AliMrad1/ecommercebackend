package com.mrad.ecommercebackend.config;

import com.mrad.ecommercebackend.user.security.JWTRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JWTRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
       http.csrf().disable().cors().disable();
       http.addFilterBefore(jwtRequestFilter, AuthorizationFilter.class);
       http.authorizeHttpRequests()
               .requestMatchers("/product/all", "/auth/register","/auth/login","/auth/confirm","/inventory/*").permitAll()
               .anyRequest().authenticated();

       return http.build();
    }

}
