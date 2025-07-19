package com.yaretzyram.alura.forohub.domains.models.course;

public record CourseOutputDTO(
        Long id,
        String name,
        Category category
) {
}
