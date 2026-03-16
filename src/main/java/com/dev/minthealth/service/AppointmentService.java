package com.dev.minthealth.service;

import com.dev.minthealth.dto.AppointmentRequest;
import com.dev.minthealth.dto.AppointmentResponse;
import org.springframework.data.domain.Page;

public interface AppointmentService {

    AppointmentResponse bookAppointment(AppointmentRequest appointmentRequest);

    Page<AppointmentResponse> getAllAppointments(int page, int size);

    AppointmentResponse cancelAppointment(Long appointmentId);

    Page<AppointmentResponse> getAppointmentsByDoctor(Long doctorId, int page, int size);

    Page<AppointmentResponse> getAppointmentsByPatient(Long patientId, int page, int size);
}
