package com.example.idevbackend.payload.request;

public record ProjectRequest(
        String title,
        String image,
        String link,
        String description) {
}