package com.nifadh.pointofsales.modules.product.stock;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    @PatchMapping("/product/{productId}/stock")
    public void updateStock (@PathVariable Integer productId, @Valid @RequestBody StockUpdateRequest stockUpdateRequest) {
        stockService.updateStock(productId, stockUpdateRequest);
    }
}
