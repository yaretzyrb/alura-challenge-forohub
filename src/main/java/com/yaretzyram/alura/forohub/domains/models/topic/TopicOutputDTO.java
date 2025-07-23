package com.yaretzyram.alura.forohub.domains.models.topic;

import com.yaretzyram.alura.forohub.domains.models.course.CourseInputDTO;
import com.yaretzyram.alura.forohub.domains.models.user.UserInputDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TopicOutputDTO(
        Long id,
        String title,
        String message,
        String authorName,
        String courseName,
        LocalDateTime createdAt
) {
}
