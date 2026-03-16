package com.dev.minthealth.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalRecordResponse {
    private Long id;
    private String doctorName;
    private String patientName;
    private String diagnosis;
    private String prescription;
}
