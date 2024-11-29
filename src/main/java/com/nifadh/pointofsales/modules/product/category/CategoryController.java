package com.nifadh.pointofsales.modules.product.category;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/category")
    public CategoryResponse addCategory(@RequestBody CategoryRequest categoryRequest) {
        return categoryService.addCategory(categoryRequest);
    }

    @GetMapping("/category")
    public CategoryResponseList getAllCategories() {
        return categoryService.getAllCategories();
    }

    @DeleteMapping("/category/{categoryId}")
    public void hardDeleteCategoryById(@PathVariable Integer categoryId) {
        categoryService.hardDeleteCategory(categoryId);
    }

}
