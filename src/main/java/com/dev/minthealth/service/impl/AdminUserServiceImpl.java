package com.dev.minthealth.service.impl;

import com.dev.minthealth.dto.AdminCreateUserRequest;
import com.dev.minthealth.dto.AdminCreateUserResponse;
import com.dev.minthealth.entity.Doctor;
import com.dev.minthealth.entity.Role;
import com.dev.minthealth.entity.User;
import com.dev.minthealth.exception.BadRequestException;
import com.dev.minthealth.repository.DoctorRepository;
import com.dev.minthealth.repository.UserRepository;
import com.dev.minthealth.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public AdminCreateUserResponse createUser(AdminCreateUserRequest request) {
        if (request.getRole() == Role.PATIENT) {
            throw new BadRequestException("Admin API cannot create PATIENT users. Use /auth/register.");
        }
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException("Email is already registered!");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepository.save(user);

        Long doctorId = null;
        if (request.getRole() == Role.DOCTOR) {
            if (request.getSpecialization() == null || request.getSpecialization().isBlank()) {
                throw new BadRequestException("Specialization is required for DOCTOR role.");
            }
            if (request.getExperience() == null) {
                throw new BadRequestException("Experience is required for DOCTOR role.");
            }

            Doctor doctor = Doctor.builder()
                .user(user)
                .specialization(request.getSpecialization())
                .experience(request.getExperience())
                .build();
            doctorRepository.save(doctor);
            doctorId = doctor.getId();
        }

        return AdminCreateUserResponse.builder()
                .userId(user.getId())
                .doctorId(doctorId)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}

