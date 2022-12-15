package com.mrad.ecommercebackend.user.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginBody(
        @NotNull
        @NotBlank
        String username,
        @NotNull
        @NotBlank
         String password
) {}
