package com.nifadh.pointofsales.modules.product.category;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class CategoryResponseList {
    private final List<CategoryResponse> categories;
}
