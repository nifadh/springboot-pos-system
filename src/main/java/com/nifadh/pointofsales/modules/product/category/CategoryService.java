package com.nifadh.pointofsales.modules.product.category;

import com.nifadh.pointofsales.exception.DuplicateResourceException;
import com.nifadh.pointofsales.exception.ResourceNotFoundException;
import com.nifadh.pointofsales.modules.commondtos.SoftDeleteRequest;
import com.nifadh.pointofsales.modules.product.Product;
import com.nifadh.pointofsales.modules.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final ProductRepository productRepository;

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

    public void hardDeleteCategoryById(Integer categoryId) {
        checkIfCategoryIdIsValid(categoryId);
        categoryRepository.deleteById(categoryId);
    }

    @Transactional
    public void changeSoftDeleteStatus(Integer categoryId, SoftDeleteRequest softDeleteRequest) {
        checkIfCategoryIdIsValid(categoryId);
        Category category = categoryRepository.findById(categoryId).get();

        if (category.getIsDeleted().equals(softDeleteRequest.getIsDeleted())) {
            return;
        }

        category.setIsDeleted(softDeleteRequest.getIsDeleted());
        categoryRepository.save(category);

        if (softDeleteRequest.getIsDeleted() == true) {
            List<Product> products = productRepository.findByCategoryIdAndIsDeletedIsFalse(categoryId);
            for (Product product : products) {
                product.setIsDeleted(softDeleteRequest.getIsDeleted());
                productRepository.save(product);
            }
        }
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
