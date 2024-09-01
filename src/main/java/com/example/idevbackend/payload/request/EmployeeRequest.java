package com.example.idevbackend.payload.request;

import com.example.idevbackend.models.enums.Language;

public record EmployeeRequest(
        String fullName,
        String image,
        String linkLinkedIn,
        Language language
) {
}