package com.yaretzyram.alura.forohub.domains.models.topic;

import java.time.LocalDateTime;

public record TopicOutputDTO(
        Long id,
        String title,
        String message,
        Status status,
        String authorName,
        String courseName,
        LocalDateTime createdAt
) {
}
