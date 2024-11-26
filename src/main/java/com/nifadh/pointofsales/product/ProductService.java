package com.nifadh.pointofsales.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductResponse addProduct(ProductRequest productRequest) {
        Product product = productMapper.productRequestToProduct(productRequest);
        product.setIsDeleted(true);
        product = productRepository.save(product);
        return productMapper.productToProductResponse(product);
    }

}
