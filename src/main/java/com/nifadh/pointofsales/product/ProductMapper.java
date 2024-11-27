package com.nifadh.pointofsales.product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    public ProductResponseList productListToProductResponseList(List<Product> productList) {
        List<ProductResponse> productResponseList = new ArrayList<>();
        productList.forEach(product -> {
            ProductResponse productResponse = productToProductResponse(product);
            productResponseList.add(productResponse);
        });
        return new ProductResponseList(productResponseList);
    }
}
