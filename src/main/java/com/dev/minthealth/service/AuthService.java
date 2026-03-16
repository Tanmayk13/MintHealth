package com.dev.minthealth.service;

import com.dev.minthealth.dto.AuthResponse;
import com.dev.minthealth.dto.LoginRequest;
import com.dev.minthealth.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
