package com.nifadh.pointofsales.modules.product;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public class ProductResponse {
    private final Integer id;
    private final String itemCode;
    private final String name;
    private final String image;
    private final String category;
    private final Double costPrice;
    private final Double sellingPrice;
    private final Boolean isDeleted;
}
