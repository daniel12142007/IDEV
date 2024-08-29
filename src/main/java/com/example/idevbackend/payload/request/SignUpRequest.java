package com.example.idevbackend.payload.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class SignUpRequest {
    @Email(message = "It should have email format")
    @NotBlank(message = "User email is required")
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(min = 8)
    private String password;

    public @Email(message = "It should have email format") @NotBlank(message = "User email is required") String getEmail() {
        return email;
    }
}
