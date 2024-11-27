package com.nifadh.pointofsales.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ProductResponseList {
    private final List<ProductResponse> products;
}
