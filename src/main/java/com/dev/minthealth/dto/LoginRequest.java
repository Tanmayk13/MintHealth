package com.dev.minthealth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    @Schema(description = "Email", example = "abc@gmail.com")
    private String email;

    @NotBlank(message = "Password is required")
    @Schema(description = "Password", example = "abc123")
    private String password;
}
