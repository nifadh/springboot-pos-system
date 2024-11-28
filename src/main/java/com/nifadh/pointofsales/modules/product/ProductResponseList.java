package com.nifadh.pointofsales.modules.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class ProductResponseList {
    private final List<ProductResponse> products;
}
