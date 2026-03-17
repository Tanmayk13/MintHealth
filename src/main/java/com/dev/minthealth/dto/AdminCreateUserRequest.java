package com.dev.minthealth.dto;

import com.dev.minthealth.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminCreateUserRequest {

    @NotBlank(message = "Name is required")
    @Schema(description = "Name", example = "Dr. Jane Doe")
    private String name;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    @Schema(description = "Email", example = "jane.doe@minthealth.com")
    private String email;

    @NotBlank(message = "Password is required")
    @Schema(description = "Password", example = "ChangeMe123")
    private String password;

    @NotNull(message = "Role is required")
    @Schema(description = "Role (ADMIN or DOCTOR)", example = "DOCTOR")
    private Role role;

    @Schema(description = "Doctor specialization (required if role=DOCTOR)", example = "Cardiology")
    private String specialization;

    @Min(value = 0, message = "Experience must be >= 0")
    @Schema(description = "Doctor years of experience (required if role=DOCTOR)", example = "5")
    private Integer experience;
}
