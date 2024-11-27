package com.nifadh.pointofsales.product;

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
        List<Product> products = productRepository.findAll();
        return productMapper.productListToProductResponseList(products);
    }







}
