package com.example.idevbackend.payload.response;

import com.example.idevbackend.models.enums.Language;

public record ProjectResponse(
        Long id,
        String title,
        String image,
        String link,
        String description,
        Language language
) {
}