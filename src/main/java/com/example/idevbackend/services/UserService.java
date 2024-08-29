package com.example.idevbackend.services;

import com.example.idevbackend.config.JwtUtils;
import com.example.idevbackend.models.User;
import com.example.idevbackend.models.enums.ERole;
import com.example.idevbackend.payload.request.LoginRequest;
import com.example.idevbackend.payload.request.SignUpRequest;
import com.example.idevbackend.payload.response.JWTTokenSuccessResponse;
import com.example.idevbackend.payload.response.MessageResponse;
import com.example.idevbackend.payload.response.UserResponse;
import com.example.idevbackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;


    public ResponseEntity<Object> signIn(LoginRequest loginRequest) {
        LOGGER.info("Попытка входа пользователя с email: {}", loginRequest.getEmail());
        if (userRepository.findUserByEmail(loginRequest.getEmail()).isEmpty()) {
            LOGGER.warn("Email {} не найден", loginRequest.getEmail());
            return new ResponseEntity<>(new MessageResponse("Email not found"), HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findUserByEmail(loginRequest.getEmail()).get();
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            LOGGER.warn("Неверный пароль для email: {}", loginRequest.getEmail());
            return new ResponseEntity<>(new MessageResponse("Incorrect password"), HttpStatus.BAD_REQUEST);
        }
        String jwt = jwtUtils.generateToken(loginRequest.getEmail());

        LOGGER.info("Вход успешен для пользователя с email: {}", loginRequest.getEmail());
        return ResponseEntity.ok(new JWTTokenSuccessResponse(true, jwt));
    }


    public ResponseEntity<Object> createUser(SignUpRequest user) {
        LOGGER.info("Попытка создания нового пользователя с email: {}", user.getEmail());

        if (userRepository.findUserByEmail(user.getEmail()).isPresent()) {
            LOGGER.warn("Email {} уже существует", user.getEmail());
            return new ResponseEntity<>(new MessageResponse("Email found. Email must be unique"), HttpStatus.BAD_REQUEST);
        }

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole(ERole.ADMIN);

        try {
            LOGGER.info("Сохранение нового пользователя с email: {}", user.getEmail());
            userRepository.save(newUser);
        } catch (Exception e) {
            LOGGER.error("Ошибка при регистрации пользователя с email: {}", user.getEmail(), e);
            return new ResponseEntity<>(new MessageResponse("The user " + newUser.getUsername() + " already exists"), HttpStatus.BAD_REQUEST);
        }

        LOGGER.info("Пользователь с email: {} успешно зарегистрирован", user.getEmail());
        return new ResponseEntity<>(new UserResponse(newUser.getId(),
                newUser.getEmail()), HttpStatus.CREATED);
    }
}