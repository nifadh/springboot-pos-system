package com.nifadh.pointofsales.modules.commondtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SoftDeleteRequest {
    @NotNull(message = "Delete status cannot be null!")
    private final Boolean isDeleted;
}
