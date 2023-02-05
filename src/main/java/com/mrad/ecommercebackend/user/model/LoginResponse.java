package com.mrad.ecommercebackend.user.model;

public record LoginResponse(
        String jwt,
        boolean success,
        String failureReason
) {
}
