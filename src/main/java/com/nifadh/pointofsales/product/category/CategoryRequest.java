package com.nifadh.pointofsales.product.category;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CategoryRequest {
    private final String name;
}
