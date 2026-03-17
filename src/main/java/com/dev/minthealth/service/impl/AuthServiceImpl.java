package com.dev.minthealth.service.impl;

import com.dev.minthealth.dto.AuthResponse;
import com.dev.minthealth.dto.LoginRequest;
import com.dev.minthealth.dto.RegisterRequest;
import com.dev.minthealth.entity.Role;
import com.dev.minthealth.entity.User;
import com.dev.minthealth.exception.BadRequestException;
import com.dev.minthealth.exception.ResourceNotFoundException;
import com.dev.minthealth.repository.PatientRepository;
import com.dev.minthealth.repository.UserRepository;
import com.dev.minthealth.security.JWTService;
import com.dev.minthealth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    @Override
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException("Email is already registered!");
        }
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.PATIENT)
                .build();

        userRepository.save(user);

        patientRepository.save(com.dev.minthealth.entity.Patient.builder()
                .user(user)
                .build());

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(token);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid Credentials!");
        }

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(token);
    }
}
