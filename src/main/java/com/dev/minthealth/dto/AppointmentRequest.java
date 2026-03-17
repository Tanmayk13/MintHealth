package com.dev.minthealth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequest {
    @NotNull(message = "Doctor ID is required")
    @Schema(description = "Doctor ID", example = "1")
    private Long doctorId;

    @NotNull(message = "Appointment time is required")
    @Schema(description = "Appointment date and time", example = "2026-03-20T10:00:00")
    private LocalDateTime appointmentTime;
}
