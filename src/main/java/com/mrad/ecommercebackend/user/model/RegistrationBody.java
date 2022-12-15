package com.mrad.ecommercebackend.user.model;

import jakarta.validation.constraints.*;

public record RegistrationBody(
        @NotNull
        @NotBlank
        @Size(min = 6)
        @Size(max = 20)
        String username,
        @NotNull
        @NotBlank
        @Size(min = 8)
        @Size(max = 32)
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$")
        String password,
        @NotNull
        @NotBlank
        @Email
        String email,
        @NotNull
        @NotBlank
        String first_name,
        @NotNull
        @NotBlank
        String last_name
)
{
}
