package com.yaretzyram.alura.forohub.domains.models.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserInputDTO(
        @NotNull
        Long id,
        @NotBlank
        String name
) {
}
