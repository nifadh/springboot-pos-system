package com.nifadh.pointofsales.modules.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public ProductResponse editProductById(Integer productId, ProductRequest productRequest) {
        Product product = productMapper.productRequestToProduct(productRequest);
        product.setId(productId);
        return productMapper.productToProductResponse(productRepository.save(product));
    }





}
