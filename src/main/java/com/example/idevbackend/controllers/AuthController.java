package com.example.idevbackend.controllers;

import com.example.idevbackend.payload.request.LoginRequest;
import com.example.idevbackend.payload.request.SignUpRequest;
import com.example.idevbackend.payload.response.MessageResponse;
import com.example.idevbackend.payload.response.UserResponse;
import com.example.idevbackend.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@PreAuthorize("permitAll()")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/sign/in")
    @Operation(summary = "Аутентификация пользователя по email и паролю")
    public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return userService.signIn(loginRequest);
    }

    @PostMapping("/sign/up")
    @Operation(summary = "Регистрация нового пользователя")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        return userService.createUser(signUpRequest);
    }
}
