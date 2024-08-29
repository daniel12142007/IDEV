package com.example.idevbackend.payload.response;

import com.example.idevbackend.models.enums.Language;

public record SubjectResponse(
        Long id,
        String image,
        String title,
        Language language,
        Long courseId
) {}