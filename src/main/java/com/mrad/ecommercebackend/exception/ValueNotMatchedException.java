package com.mrad.ecommercebackend.exception;

public class ValueNotMatchedException extends RuntimeException {
    public ValueNotMatchedException(String message) {
        super(message);
    }
}
