package com.example.idevbackend.payload.response;

import com.example.idevbackend.models.enums.Language;

public record CourseResponse(
        Long id,
        String title,
        String description,
        Language language
) {}