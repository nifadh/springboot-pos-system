package com.nifadh.pointofsales.exception.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public class GeneralExceptionResponse {
    private final Integer statusCode;
    private final String message;
    private final String timestamp;
}
