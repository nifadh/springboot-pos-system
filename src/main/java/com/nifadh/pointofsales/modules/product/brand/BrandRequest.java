package com.nifadh.pointofsales.modules.product.brand;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BrandRequest {
    @NotBlank(message = "Brand name cannot be empty!")
    private final String name;
}
