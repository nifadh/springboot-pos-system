package com.nifadh.pointofsales.modules.product.stock;

import com.nifadh.pointofsales.validation.annotations.IsDouble;
import com.nifadh.pointofsales.validation.annotations.IsInteger;
import com.nifadh.pointofsales.validation.groups.First;
import com.nifadh.pointofsales.validation.groups.Second;
import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@GroupSequence({StockUpdateRequest.class, First.class, Second.class})
public class StockUpdateRequest {
    @NotBlank(message = "Quantity cannot be empty!", groups = {First.class})
    @IsInteger(message = "Quantity must be a valid integer", groups = {Second.class})
    private final String quantity;

    @NotBlank(message = "Weight cannot be empty!", groups = {First.class})
    @IsDouble(message = "Weight must be valid!", groups = {Second.class})
    private final String weight;

    @IsInteger(message = "Branch ID must be a valid integer!")
    @NotBlank(message = "Branch ID cannot be blank!")
    private String branchId;

    public Integer getQuantity() {
        return Integer.parseInt(this.quantity);
    }

    public Double getWeight() {
        return Double.parseDouble(this.weight);
    }

    public Integer getBranchId() {
        return Integer.parseInt(this.branchId);
    }
}
