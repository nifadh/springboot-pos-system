package com.nifadh.pointofsales.modules.product.brand;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class BrandResponseList {
    private final List<BrandResponse> brands;
}
