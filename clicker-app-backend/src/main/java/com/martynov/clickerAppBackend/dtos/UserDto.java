package com.martynov.clickerAppBackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private Long coinsAmount;
    private Long boxesAmount;
}
