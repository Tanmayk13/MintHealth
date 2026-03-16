package com.dev.minthealth.dto;

import com.dev.minthealth.entity.AppointmentStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentResponse {
    private Long id;
    private String doctorName;
    private String patientName;
    private LocalDateTime appointmentTime;
    private AppointmentStatus appointmentStatus;
}
