package com.nifadh.pointofsales.exception.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class ValidationExceptionResponse {
    private final Integer statusCode;
    private final List<String> message;
    private final String timestamp;
}
