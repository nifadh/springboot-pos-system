package com.nifadh.pointofsales.modules.product.category;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper {
    public Category categoryRequestToCategory(CategoryRequest categoryRequest) {
        return Category.builder()
                .name(categoryRequest.getName())
                .isDeleted(false)
                .build();
    }

    public CategoryResponse categoryToCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public CategoryResponseList categoryListToCategoryResponseList(List<Category> categories) {
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        categories.forEach(category -> {
            CategoryResponse categoryResponse = CategoryResponse.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .build();
            categoryResponses.add(categoryResponse);
        });
        return new CategoryResponseList(categoryResponses);
    }
}
