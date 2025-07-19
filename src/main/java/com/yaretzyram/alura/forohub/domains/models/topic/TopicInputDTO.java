package com.yaretzyram.alura.forohub.domains.models.topic;

import com.yaretzyram.alura.forohub.domains.models.course.CourseInputDTO;
import com.yaretzyram.alura.forohub.domains.models.user.UserInputDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicInputDTO(

        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotNull
        UserInputDTO author,
        @NotNull
        CourseInputDTO courseName
) {


}
