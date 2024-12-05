package com.nifadh.pointofsales.modules.product;

import com.nifadh.pointofsales.modules.product.brand.BrandResponse;
import com.nifadh.pointofsales.modules.product.category.Category;
import com.nifadh.pointofsales.modules.product.category.CategoryResponse;
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
    private final CategoryResponse category;
    private final BrandResponse brand;
    private final Double costPrice;
    private final Double sellingPrice;
}
