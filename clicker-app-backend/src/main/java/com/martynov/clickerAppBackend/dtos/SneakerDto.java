package com.martynov.clickerAppBackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SneakerDto {
    private Long id;
    private String name;
    private String description;
    private Float dropChance;
    private String imagePath;
    private Boolean isPresent;
}
