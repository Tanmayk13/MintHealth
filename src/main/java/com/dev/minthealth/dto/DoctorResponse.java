package com.dev.minthealth.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorResponse {
    private Long id;
    private String name;
    private String email;
    private String specialization;
    private int experience;
}
