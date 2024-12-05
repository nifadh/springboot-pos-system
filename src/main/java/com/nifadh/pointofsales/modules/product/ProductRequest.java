package com.nifadh.pointofsales.modules.product;

import com.nifadh.pointofsales.validation.annotations.IsDouble;
import com.nifadh.pointofsales.validation.annotations.IsInteger;
import com.nifadh.pointofsales.validation.groups.First;
import com.nifadh.pointofsales.validation.groups.Second;
import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@GroupSequence({ProductRequest.class, First.class, Second.class})
public class ProductRequest {

    @NotBlank(message = "Product name cannot be empty!", groups = {First.class})
    private final String name;

    @NotBlank(message = "Product image cannot be empty!", groups = {First.class})
    private final String image;

    @IsInteger(message = "Category ID must be a valid integer", groups = {Second.class})
    @NotBlank(message = "Category ID cannot be empty!", groups = {First.class})
    private final String categoryId;

    @NotBlank(message = "Brand ID cannot be empty!", groups = {First.class})
    @IsInteger(message = "Brand ID must be a valid integer", groups = {Second.class})
    private final String brandId;

    @NotBlank(message = "Cost price cannot be empty!", groups = {First.class})
    @IsDouble(message = "Cost price must be valid!", groups = {Second.class})
    private final String costPrice;

    @NotBlank(message = "Selling price cannot be empty!", groups = {First.class})
    @IsDouble(message = "Selling price must be valid!", groups = {Second.class})
    private final String sellingPrice;


    public Double getCostPrice() {
        return Double.parseDouble(this.costPrice);
    }

    public Double getSellingPrice() {
        return Double.parseDouble(this.sellingPrice);
    }

    public Integer getBrandId() {
        return Integer.parseInt(this.brandId);
    }

    public Integer getCategoryId() {
        return Integer.parseInt(this.categoryId);
    }
}
