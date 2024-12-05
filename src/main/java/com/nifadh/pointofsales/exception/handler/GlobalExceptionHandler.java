package com.nifadh.pointofsales.exception.handler;

import com.nifadh.pointofsales.exception.DuplicateResourceException;
import com.nifadh.pointofsales.exception.ResourceNotFoundException;
import com.nifadh.pointofsales.exception.dto.GeneralExceptionResponse;
import com.nifadh.pointofsales.exception.dto.ValidationExceptionResponse;
import com.nifadh.pointofsales.util.TimeUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;
import java.util.stream.Collectors;

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


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleException(ResourceNotFoundException ex) {
        GeneralExceptionResponse response = GeneralExceptionResponse.builder()
                .message(ex.getMessage())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timestamp(TimeUtil.getSriLankanDateTimeString())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleException(MethodArgumentNotValidException ex) {
        List<String> errorMessages = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toList());

        ValidationExceptionResponse exceptionResponse = new ValidationExceptionResponse(HttpStatus.BAD_REQUEST.value(),errorMessages, TimeUtil.getSriLankanDateTimeString());
        return ResponseEntity
                .badRequest()
                .body(exceptionResponse);
    }

}
