package com.nifadh.pointofsales.modules.product;
import com.nifadh.pointofsales.modules.product.brand.Brand;
import com.nifadh.pointofsales.modules.product.brand.BrandMapper;
import com.nifadh.pointofsales.modules.product.category.Category;
import com.nifadh.pointofsales.modules.product.category.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final CategoryMapper categoryMapper;
    private final BrandMapper brandMapper;

    public Product productRequestToProduct(ProductRequest productRequest, Category category, Brand brand) {
        return Product.builder()
                .itemCode(productRequest.getItemCode())
                .name(productRequest.getName())
                .image(productRequest.getImage())
                .category(category)
                .brand(brand)
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
                .category(
                        categoryMapper.categoryToCategoryResponse(product.getCategory())
                )
                .brand(
                        brandMapper.brandToBrandResponse(product.getBrand())
                )
                .costPrice(product.getCostPrice())
                .sellingPrice(product.getSellingPrice())
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
