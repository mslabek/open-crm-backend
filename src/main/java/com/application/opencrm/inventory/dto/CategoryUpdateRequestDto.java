package com.application.opencrm.inventory.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryUpdateRequestDto {

    @Schema(description = "Category name", example = "PC components")
    private final String name;

    @Schema(description = "Category description", example = "Hardware parts for building a personal computer")
    private final String description;

}
