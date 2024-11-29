package com.nifadh.pointofsales.modules.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SoftDeleteRequest {
    private final Boolean isDeleted;
}
