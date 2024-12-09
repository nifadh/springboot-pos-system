package com.nifadh.pointofsales.modules.customer;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

@RequiredArgsConstructor
@Getter
public class CustomerRequest {

    @NotBlank(message = "Customer name cannot be empty!")
    private final String name;

    @Length(min = 9, max = 10, message = "Phone number must be between 9 and 10 characters long!")
    @NotBlank(message = "Phone number cannot be empty!")
    private final String phone;

}
