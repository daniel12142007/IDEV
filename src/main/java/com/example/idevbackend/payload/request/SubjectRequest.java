package com.example.idevbackend.payload.request;

import javax.validation.constraints.NotBlank;

public record SubjectRequest(
        @NotBlank(message = "Image cannot be empty or null")
        String image,
        @NotBlank(message = "Title cannot be empty or null")
        String title
){}