package com.dev.minthealth.controller;

import com.dev.minthealth.dto.AuthResponse;
import com.dev.minthealth.dto.LoginRequest;
import com.dev.minthealth.dto.RegisterRequest;
import com.dev.minthealth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "APIs for user registration and login")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @Operation(
            summary = "Register a new user",
            description = "Creates a new PATIENT user account"
    )
    public AuthResponse register(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Registration Request")
            @Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    @Operation(
            summary = "Login existing user",
            description = "Login and access protected API endpoints"
    )
    public AuthResponse login(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Login Request")
            @Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
