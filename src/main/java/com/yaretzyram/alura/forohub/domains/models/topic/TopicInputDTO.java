package com.yaretzyram.alura.forohub.domains.models.topic;

import com.yaretzyram.alura.forohub.domains.models.course.Course;
import com.yaretzyram.alura.forohub.domains.models.course.CourseInputDTO;
import com.yaretzyram.alura.forohub.domains.models.course.CourseOutputDTO;
import com.yaretzyram.alura.forohub.domains.models.user.User;
import com.yaretzyram.alura.forohub.domains.models.user.UserInputDTO;
import com.yaretzyram.alura.forohub.domains.models.user.UserOutputDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicInputDTO(

        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotNull
        Long authorId,
        @NotNull
        Long courseId
) {


}
