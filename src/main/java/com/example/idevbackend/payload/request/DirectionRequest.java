package com.example.idevbackend.payload.request;

import com.example.idevbackend.models.enums.Language;

public record DirectionRequest(
        String title,
        Language language
) {}