package com.example.idevbackend.payload.request;

import com.example.idevbackend.models.enums.Language;

import javax.validation.constraints.NotBlank;

public record CourseRequest(
        @NotBlank(message = "Title cannot be empty or null")
        String title,

        @NotBlank(message = "Description cannot be empty or null")
        String description,
        @NotBlank(message = "Language cannot be empty or null")
        Language language
) {
}
