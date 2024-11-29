package com.nifadh.pointofsales.modules.product.category;

import com.nifadh.pointofsales.exception.DuplicateResourceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryResponse addCategory(CategoryRequest categoryRequest) {
        checkForDuplicateCategory(categoryRequest.getName());
        Category category = Category.builder()
                .name(categoryRequest.getName())
                .isDeleted(false)
                .build();
        category = categoryRepository.save(category);
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }


    public CategoryResponseList getAllCategories() {
        List<Category> categories = categoryRepository.findByIsDeletedIsFalse();
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


    private void checkForDuplicateCategory(String category) {
        if (categoryRepository.existsByNameEqualsIgnoreCase(category)) {
            throw new DuplicateResourceException(
                    "Category: " + category + " already exists!"
            );
        }
    }

}
