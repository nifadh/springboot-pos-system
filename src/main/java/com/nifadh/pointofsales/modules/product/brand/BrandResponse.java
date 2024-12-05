package com.nifadh.pointofsales.modules.product.brand;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
public class BrandResponse {
    private final Integer id;
    private final String name;
}
