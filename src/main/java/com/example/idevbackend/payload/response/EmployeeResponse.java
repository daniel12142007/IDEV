package com.example.idevbackend.payload.response;

public record EmployeeResponse(
        Long id,
        String fullName,
        String image,
        String linkLinkedIn,
        String direction
) {
}