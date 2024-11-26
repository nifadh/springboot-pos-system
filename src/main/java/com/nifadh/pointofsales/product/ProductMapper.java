package com.nifadh.pointofsales.product;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.NaturalId;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product productRequestToProduct(ProductRequest productRequest) {
        return Product.builder()
                .itemCode(productRequest.getItemCode())
                .name(productRequest.getName())
                .image(productRequest.getImage())
                .category(productRequest.getImage())
                .costPrice(productRequest.getCostPrice())
                .sellingPrice(productRequest.getSellingPrice())
                .build();
    }

    public ProductResponse productToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .itemCode(product.getItemCode())
                .name(product.getName())
                .image(product.getImage())
                .category(product.getCategory())
                .costPrice(product.getCostPrice())
                .sellingPrice(product.getSellingPrice())
                .isDeleted(product.getIsDeleted())
                .build();
    }
}
