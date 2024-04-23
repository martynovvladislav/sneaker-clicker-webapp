package com.martynov.clickerAppBackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiErrorResponseDto {
    private String exceptionName;
    private String code;
}
