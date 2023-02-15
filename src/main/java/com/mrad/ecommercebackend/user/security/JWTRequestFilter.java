package com.mrad.ecommercebackend.user.security;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.mrad.ecommercebackend.user.dao.UserDao;
import com.mrad.ecommercebackend.user.model.UserModel;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService service;
    @Autowired
    private UserDao repository;

    public JWTRequestFilter(JWTService service, UserDao repository) {
        this.service = service;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        var tokenHeader = request.getHeader("Authorization");
        if(tokenHeader != null && tokenHeader.startsWith("Bearer ")){
            var token = tokenHeader.substring(7);
            try {
                var username = service.getUsername(token);
                System.out.println(username);
                Optional<UserModel> userExist = repository.findByUsername(username);
                if (userExist.isPresent()) {
                    var user = userExist.get();
                    var authenticationToken = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }else{
                    System.out.println("Not found!!!!!!!!!!!");
                }
            } catch (JWTDecodeException exception){

            }
        }

        filterChain.doFilter(request, response);

    }
}
