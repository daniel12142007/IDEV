package com.example.idevbackend.payload.response;

import com.example.idevbackend.models.enums.Language;

public record EmployeeResponse(
        Long id,
        String fullName,
        String image,
        String linkLinkedIn,
        Language language,
        String directions
) {
}