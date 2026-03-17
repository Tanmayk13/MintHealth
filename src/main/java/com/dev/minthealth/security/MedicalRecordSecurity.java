package com.dev.minthealth.security;

import com.dev.minthealth.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("medicalRecordSecurity")
@RequiredArgsConstructor
public class MedicalRecordSecurity {

    private final AppointmentRepository appointmentRepository;

    public boolean isOwner(Authentication authentication, Long appointmentId) {
        if (authentication == null || appointmentId == null) {
            return false;
        }
        return appointmentRepository.existsByIdAndPatientUserEmail(appointmentId, authentication.getName());
    }
}
