package com.nifadh.pointofsales.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductResponse addProduct(ProductRequest productRequest) {
        Product product = productMapper.productRequestToProduct(productRequest);
        product.setIsDeleted(false);
        product = productRepository.save(product);
        return productMapper.productToProductResponse(product);
    }

    public ProductResponseList getAllProducts() {
        List<Product> products = productRepository.findByIsDeletedIsFalse();
        return productMapper.productListToProductResponseList(products);
    }

    public void softDeleteProduct(Integer productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        product.setIsDeleted(true);
        productRepository.save(product);
    }



}
