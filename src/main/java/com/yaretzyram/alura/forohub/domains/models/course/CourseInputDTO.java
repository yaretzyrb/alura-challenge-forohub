package com.yaretzyram.alura.forohub.domains.models.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseInputDTO(
        @NotBlank
        String name,
        @NotNull
        Category category
) {
}
