package com.nifadh.pointofsales.modules.product;

import com.nifadh.pointofsales.modules.commondtos.SoftDeleteRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Validated
public class ProductController {
    private final ProductService productService;

    @PostMapping("/product")
    public ProductResponse addProduct(@Valid @RequestBody ProductRequest productRequest) {
        return productService.addProduct(productRequest);
    }

    @GetMapping("/product")
    public ProductResponseList getAllProducts() {
        return productService.getAllProducts();
    }

    @PatchMapping("/product/{productId}")
    public void changeSoftDeleteStatus(
            @PathVariable Integer productId,
            @RequestBody SoftDeleteRequest softDeleteRequest
    ) {
        productService.changeSoftDeleteStatus(productId, softDeleteRequest);
    }

    @PutMapping("/product/{productId}")
    public ProductResponse editProduct(
            @PathVariable Integer productId,
            @RequestBody ProductRequest productRequest
    ) {
        return productService.editProductById(productId, productRequest);
    }

    @DeleteMapping("/product/{productId}")
    public void hardDeleteProductById(@PathVariable Integer productId) {
        productService.hardDeleteProduct(productId);
    }

}
