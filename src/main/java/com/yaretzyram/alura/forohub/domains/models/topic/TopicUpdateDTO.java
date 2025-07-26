package com.yaretzyram.alura.forohub.domains.models.topic;

import jakarta.validation.constraints.NotBlank;

public record TopicUpdateDTO(
        @NotBlank
        String title,
        @NotBlank
        String message
) {
}
