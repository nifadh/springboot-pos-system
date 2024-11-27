package com.nifadh.pointofsales.product;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/product")
    public ProductResponse addProduct(@RequestBody ProductRequest productRequest) {
        return productService.addProduct(productRequest);
    }

    @GetMapping("/product")
    public ProductResponseList getAllProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping("/product/{productId}")
    public void softDeleteProduct(@PathVariable Integer productId) {
        productService.softDeleteProduct(productId);
    }

}
