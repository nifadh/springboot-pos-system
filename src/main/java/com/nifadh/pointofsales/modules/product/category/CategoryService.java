package com.nifadh.pointofsales.modules.product.category;

import com.nifadh.pointofsales.exception.DuplicateResourceException;
import com.nifadh.pointofsales.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryResponse addCategory(CategoryRequest categoryRequest) {
        checkForDuplicateCategory(categoryRequest.getName());
        Category category = categoryMapper.categoryRequestToCategory(categoryRequest);
        category = categoryRepository.save(category);
        return categoryMapper.categoryToCategoryResponse(category);
    }

    public Category findCategoryById(Integer categoryId) {
        checkIfCategoryIdIsValid(categoryId);
        return categoryRepository.findById(categoryId).get();
    }




    public CategoryResponseList getAllCategories() {
        List<Category> categories = categoryRepository.findByIsDeletedIsFalse();
        return categoryMapper.categoryListToCategoryResponseList(categories);
    }


    private void checkForDuplicateCategory(String category) {
        if (categoryRepository.existsByNameEqualsIgnoreCase(category)) {
            throw new DuplicateResourceException(
                    "Category: " + category + " already exists!"
            );
        }
    }

    public void checkIfCategoryIdIsValid(Integer categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException(
                    "Category with ID: " + categoryId + " does not exist!"
            );
        }
    }

}
