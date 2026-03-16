package com.dev.minthealth.dto;

import com.dev.minthealth.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "Name is required")
    @Schema(description = "Name", example = "John")
    private String name;

    @Email(message = "Invalid email")
    @NotBlank(message = "Name is required")
    @Schema(description = "Email", example = "john@gmail.com")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @Schema(description = "Password", example = "john123")
    private String password;

    @NotNull(message = "Role is required")
    @Schema(description = "Role", example = "PATIENT")
    private Role role;
}
