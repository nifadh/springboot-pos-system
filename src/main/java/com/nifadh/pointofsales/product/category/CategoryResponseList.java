package com.nifadh.pointofsales.product.category;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class CategoryResponseList {
    private final List<CategoryResponse> categories;
}
