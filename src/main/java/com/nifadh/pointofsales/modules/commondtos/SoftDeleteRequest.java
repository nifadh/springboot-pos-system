package com.nifadh.pointofsales.modules.commondtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SoftDeleteRequest {
    private final Boolean isDeleted;
}
