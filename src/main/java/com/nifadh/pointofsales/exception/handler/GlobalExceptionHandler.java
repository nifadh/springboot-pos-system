package com.nifadh.pointofsales.exception.handler;

import com.nifadh.pointofsales.exception.DuplicateResourceException;
import com.nifadh.pointofsales.exception.dto.GeneralExceptionResponse;
import com.nifadh.pointofsales.util.TimeUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<?> handleException(DuplicateResourceException ex) {
        GeneralExceptionResponse response = GeneralExceptionResponse.builder()
                .message(ex.getMessage())
                .statusCode(HttpStatus.CONFLICT.value())
                .timestamp(TimeUtil.getSriLankanDateTimeString())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
