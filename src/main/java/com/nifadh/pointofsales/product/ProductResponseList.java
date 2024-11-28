package com.nifadh.pointofsales.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class ProductResponseList {
    private final List<ProductResponse> products;
}
