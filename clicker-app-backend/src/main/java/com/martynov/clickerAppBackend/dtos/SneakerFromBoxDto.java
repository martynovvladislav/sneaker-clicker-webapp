package com.martynov.clickerAppBackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SneakerFromBoxDto {
    private Long id;
    private String name;
    private Float dropChance;
    private String imagePath;
    private Boolean isNew;
}
