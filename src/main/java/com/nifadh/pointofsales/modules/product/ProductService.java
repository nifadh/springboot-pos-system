package com.nifadh.pointofsales.modules.product;

import com.nifadh.pointofsales.exception.DuplicateResourceException;
import com.nifadh.pointofsales.exception.ResourceNotFoundException;
import com.nifadh.pointofsales.modules.commondtos.SoftDeleteRequest;
import com.nifadh.pointofsales.modules.product.category.Category;
import com.nifadh.pointofsales.modules.product.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;



    //crud operations
    public ProductResponse addProduct(ProductRequest productRequest) {
        checkForDuplicateProduct(productRequest.getName());
        Category category = categoryService.findCategoryById(productRequest.getCategoryId());

        Product product = productMapper.productRequestToProduct(productRequest, category);
        product.setIsDeleted(false);
        product = productRepository.save(product);
        return productMapper.productToProductResponse(product);
    }

    public ProductResponseList getAllProducts() {
        List<Product> products = productRepository.findByIsDeletedIsFalse();
        return productMapper.productListToProductResponseList(products);
    }


    public void changeSoftDeleteStatus(Integer productId, SoftDeleteRequest softDeleteRequest) {
        checkIfProductIdIsValid(productId);
        Product product = productRepository.findById(productId).orElseThrow();
        product.setIsDeleted(softDeleteRequest.getIsDeleted());
        productRepository.save(product);
    }


    public ProductResponse editProductById(Integer productId, ProductRequest productRequest) {
        checkIfProductIdIsValid(productId);
        Category category = categoryService.findCategoryById(productRequest.getCategoryId());
        Product product = productMapper.productRequestToProduct(productRequest, category);
        product.setId(productId);
        return productMapper.productToProductResponse(productRepository.save(product));
    }


    public void hardDeleteProduct(Integer productId) {
        checkIfProductIdIsValid(productId);
        productRepository.deleteById(productId);
    }




    //validations
    private void checkForDuplicateProduct(String name) {
        if (productRepository.existsByNameEqualsIgnoreCase(name)) {
            throw new DuplicateResourceException(
                    "Product: '" + name + "' already exists!"
            );
        }
    }

    private void checkIfProductIdIsValid(Integer productId) {
        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException(
                    "Product with ID: " + productId + " does not exist!"
            );
        }
    }





}
