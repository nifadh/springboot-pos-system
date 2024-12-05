package com.nifadh.pointofsales.modules.product.brand;

import com.nifadh.pointofsales.modules.commondtos.SoftDeleteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @PostMapping("/brand")
    public BrandResponse addCategory(@RequestBody BrandRequest brandRequest) {
        return brandService.addBrand(brandRequest);
    }

    @GetMapping("/brand")
    public BrandResponseList getAllBrands() {
        return brandService.getAllBrands();
    }

    @DeleteMapping("/brand/{brandId}")
    public void hardDeleteBrandById(@PathVariable Integer brandId) {
        brandService.hardDeleteBrandById(brandId);
    }

    @PatchMapping("/brand/{brandId}")
    public void changeSoftDeleteStatus(
            @PathVariable Integer brandId,
            @RequestBody SoftDeleteRequest softDeleteRequest
    ) {
        brandService.changeSoftDeleteStatus(brandId, softDeleteRequest);
    }
}
