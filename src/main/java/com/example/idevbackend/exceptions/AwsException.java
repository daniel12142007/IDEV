package com.example.idevbackend.exceptions;

public class AwsException extends RuntimeException {
    public AwsException(String message) {
        super(message);
    }
}