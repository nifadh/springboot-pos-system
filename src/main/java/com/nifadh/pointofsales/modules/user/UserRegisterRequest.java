package com.nifadh.pointofsales.modules.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserRegisterRequest {
    @NotBlank(message = "First name should not be empty")
    private final String firstName;

    @NotBlank(message = "Last name should not be empty")
    private final String lastName;

    @NotBlank(message = "Password should not be empty")
    private final String password;

    @NotBlank(message = "Email cannot be empty!")
    @Email(message = "Enter a valid email")
    private final String email;

    @NotBlank(message = "Role should not be empty")
    @Pattern(regexp = "^(?i)(CASHIER|BRANCH_MANAGEr|REGIONAL_MANAGER|ADMIN)$", message = "Enter a valid role!")
    private final String role;
}
