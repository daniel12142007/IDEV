package com.example.idevbackend.payload.response;

import com.example.idevbackend.models.enums.Language;

public record DiplomaResponse(Long id,
                              String title,
                              Language language,
                              Long courseId) {
}