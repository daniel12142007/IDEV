package com.example.idevbackend.payload.response;

import com.example.idevbackend.models.enums.Language;

public record DirectionResponse(
        Long id,
        String title,
        Language language
) {}