package com.nifadh.pointofsales.modules.product.category;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
public class CategoryResponse {
    private final Integer id;
    private final String name;
}
