package com.example.idevbackend.payload.request;

import javax.validation.constraints.NotBlank;

public record CourseUpdateRequest(
        @NotBlank(message = "Title cannot be empty or null")
        String title,
        @NotBlank(message = "Description cannot be empty or null")
        String description
) {}