package com.nifadh.pointofsales.modules.product.brand;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BrandMapper {
    public Brand brandRequestToBrand(BrandRequest brandRequest) {
        return Brand.builder()
                .name(brandRequest.getName())
                .isDeleted(false)
                .build();
    }

    public BrandResponse brandToBrandResponse(Brand brand) {
        return BrandResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .build();
    }

    public BrandResponseList brandListToBrandResponseList(List<Brand> brands) {
        List<BrandResponse> brandResponses = new ArrayList<>();
        brands.forEach(brand -> {
            brandResponses.add(brandToBrandResponse(brand));
        });
        return new BrandResponseList(brandResponses);
    }
}
