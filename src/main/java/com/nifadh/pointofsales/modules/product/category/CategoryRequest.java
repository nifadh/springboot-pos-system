package com.nifadh.pointofsales.modules.product.category;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CategoryRequest {
    private final String name;
}
