package com.martynov.clickerAppBackend.exceptions;

import com.martynov.clickerAppBackend.dtos.ApiErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ApiErrorResponseDto> userAlreadyExist(
            UserAlreadyExistException exception
    ) {
        return new ResponseEntity<>(
                new ApiErrorResponseDto(
                        exception.getClass().getSimpleName(),
                        HttpStatus.BAD_REQUEST.toString()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity<ApiErrorResponseDto> userAlreadyExist(
            UserDoesNotExistException exception
    ) {
        return new ResponseEntity<>(
                new ApiErrorResponseDto(
                        exception.getClass().getSimpleName(),
                        HttpStatus.BAD_REQUEST.toString()
                ),
                HttpStatus.BAD_REQUEST
        );
    }
}
