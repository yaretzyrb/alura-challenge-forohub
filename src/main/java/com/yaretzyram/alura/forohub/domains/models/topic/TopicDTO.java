package com.yaretzyram.alura.forohub.domains.models.topic;

public record TopicDTO(
        Long userId,
        String message,
        String name,
        String title
) {


}
