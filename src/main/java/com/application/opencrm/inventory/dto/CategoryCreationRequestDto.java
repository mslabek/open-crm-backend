package com.application.opencrm.inventory.dto;

import com.application.opencrm.inventory.model.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

/**
 * Data transfer object of a request to create or update a {@link Category}.
 */
@Builder
@Getter
@AllArgsConstructor
public class CategoryCreationRequestDto {

    @Schema(description = "Category name", example = "PC components", required = true)
    @NotBlank(message = "Category name cannot be blank")
    private final String name;

    @Schema(description = "Category description", example = "Hardware parts for building a personal computer")
    private final String description;

}
