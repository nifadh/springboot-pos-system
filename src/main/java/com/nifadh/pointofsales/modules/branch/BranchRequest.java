package com.nifadh.pointofsales.modules.branch;

import com.nifadh.pointofsales.validation.annotations.IsInteger;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class BranchRequest {
    @NotBlank(message = "Name cannot be blank!")
    private String name;

    @IsInteger(message = "Manager ID must be a valid integer!")
    @NotBlank(message = "Manager ID cannot be blank!")
    private String managerId;

    public Integer getManagerId() {
        return Integer.parseInt(managerId);
    }
}
