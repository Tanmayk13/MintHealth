package com.dev.minthealth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalRecordRequest {
    @NotNull(message = "Appointment ID is required")
    @Schema(description = "Appointment ID", example = "1")
    private Long appointmentId;

    @NotBlank(message = "Diagnosis is required")
    @Schema(description = "Diagnosis", example = "Cough")
    private String diagnosis;

    @NotBlank(message = "Prescription is required")
    @Schema(description = "Prescription", example = "Drink warm ginger tea")
    private String prescription;
}
