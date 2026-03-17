package com.dev.minthealth.controller;

import com.dev.minthealth.dto.AdminCreateUserRequest;
import com.dev.minthealth.dto.AdminCreateUserResponse;
import com.dev.minthealth.service.AdminUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/users")
@Tag(name = "Admin", description = "Admin-only user management APIs")
public class AdminUserController {

    private final AdminUserService adminUserService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create an ADMIN or DOCTOR user")
    public ResponseEntity<AdminCreateUserResponse> createUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Admin user creation request")
            @Valid @RequestBody AdminCreateUserRequest request) {
        return ResponseEntity.ok(adminUserService.createUser(request));
    }
}

