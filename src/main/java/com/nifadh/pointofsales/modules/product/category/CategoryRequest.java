package com.nifadh.pointofsales.modules.product.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CategoryRequest {
    @NotBlank(message = "Category name cannot be empty!")
    private final String name;
}
