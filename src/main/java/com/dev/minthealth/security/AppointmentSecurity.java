package com.dev.minthealth.security;

import com.dev.minthealth.repository.DoctorRepository;
import com.dev.minthealth.repository.AppointmentRepository;
import com.dev.minthealth.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("appointmentSecurity")
@RequiredArgsConstructor
public class AppointmentSecurity {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public boolean isOwner(Authentication authentication, Long appointmentId) {
        if (authentication == null || appointmentId == null) {
            return false;
        }
        return appointmentRepository.existsByIdAndPatientUserEmail(appointmentId, authentication.getName());
    }

    public boolean isPatient(Authentication authentication, Long patientId) {
        if (authentication == null || patientId == null) {
            return false;
        }
        return patientRepository.existsByIdAndUserEmail(patientId, authentication.getName());
    }

    public boolean isDoctor(Authentication authentication, Long doctorId) {
        if (authentication == null || doctorId == null) {
            return false;
        }
        return doctorRepository.existsByIdAndUserEmail(doctorId, authentication.getName());
    }
}
