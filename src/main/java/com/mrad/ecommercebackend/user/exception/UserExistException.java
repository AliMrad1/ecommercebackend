package com.mrad.ecommercebackend.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserExistException extends Exception {

    public UserExistException(String message) {
        super(message);
    }
}
