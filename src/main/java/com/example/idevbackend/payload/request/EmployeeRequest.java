package com.example.idevbackend.payload.request;

public record EmployeeRequest(
        String fullName,
        String image,
        String linkLinkedIn
        ) {
}