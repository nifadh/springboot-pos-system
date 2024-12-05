package com.nifadh.pointofsales.modules.product.brand;

import com.nifadh.pointofsales.exception.DuplicateResourceException;
import com.nifadh.pointofsales.exception.ResourceNotFoundException;
import com.nifadh.pointofsales.modules.commondtos.SoftDeleteRequest;
import com.nifadh.pointofsales.modules.product.Product;
import com.nifadh.pointofsales.modules.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;
    private final ProductRepository productRepository;

    public BrandResponse addBrand(BrandRequest brandRequest) {
        checkForDuplicateBrand(brandRequest.getName());
        Brand brand = brandMapper.brandRequestToBrand(brandRequest);
        brand = brandRepository.save(brand);
        return brandMapper.brandToBrandResponse(brand);
    }

    public Brand findBrandById(Integer brandId) {
        checkIfBrandIdIsValid(brandId);
        return brandRepository.findById(brandId).get();
    }

    public void hardDeleteBrandById(Integer brandId) {
        checkIfBrandIdIsValid(brandId);
        brandRepository.deleteById(brandId);
    }

    @Transactional
    public void changeSoftDeleteStatus(Integer brandId, SoftDeleteRequest softDeleteRequest) {
        checkIfBrandIdIsValid(brandId);
        Brand brand = brandRepository.findById(brandId).get();

        if (brand.getIsDeleted().equals(softDeleteRequest.getIsDeleted())) {
            return;
        }

        brand.setIsDeleted(softDeleteRequest.getIsDeleted());
        brandRepository.save(brand);

        if (softDeleteRequest.getIsDeleted()) {
            List<Product> products = productRepository.findByBrandIdAndIsDeletedIsFalse(brandId);
            for (Product product : products) {
                product.setIsDeleted(softDeleteRequest.getIsDeleted());
                productRepository.save(product);
            }
        }
    }



    public BrandResponseList getAllBrands() {
        List<Brand> brands = brandRepository.findByIsDeletedIsFalse();
        return brandMapper.brandListToBrandResponseList(brands);
    }


    private void checkForDuplicateBrand(String brand) {
        if (brandRepository.existsByNameEqualsIgnoreCase(brand)) {
            throw new DuplicateResourceException(
                    "Brand: " + brand + " already exists!"
            );
        }
    }

    public void checkIfBrandIdIsValid(Integer brandId) {
        if (!brandRepository.existsById(brandId)) {
            throw new ResourceNotFoundException(
                    "Brand with ID: " + brandId + " does not exist!"
            );
        }
    }
}
