package com.nifadh.pointofsales.modules.product;

import com.nifadh.pointofsales.exception.DuplicateResourceException;
import com.nifadh.pointofsales.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;



    //crud operations
    public ProductResponse addProduct(ProductRequest productRequest) {
        checkForDuplicateProduct(productRequest.getName());
        Product product = productMapper.productRequestToProduct(productRequest);
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
        Product product = productMapper.productRequestToProduct(productRequest);
        product.setId(productId);
        return productMapper.productToProductResponse(productRepository.save(product));
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
