package com.nifadh.pointofsales.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NaturalId;

@RequiredArgsConstructor
@Getter
public class ProductRequest {
    private final String itemCode;

    private final String name;

    private final String image;

    private final String category;

    private final Double costPrice;

    private final Double sellingPrice;
}
