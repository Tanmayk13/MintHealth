package com.dev.minthealth.service.impl;

import com.dev.minthealth.dto.AppointmentRequest;
import com.dev.minthealth.dto.AppointmentResponse;
import com.dev.minthealth.entity.Appointment;
import com.dev.minthealth.entity.AppointmentStatus;
import com.dev.minthealth.entity.Doctor;
import com.dev.minthealth.entity.Patient;
import com.dev.minthealth.exception.ResourceNotFoundException;
import com.dev.minthealth.repository.AppointmentRepository;
import com.dev.minthealth.repository.DoctorRepository;
import com.dev.minthealth.repository.PatientRepository;
import com.dev.minthealth.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Override
    public AppointmentResponse bookAppointment(AppointmentRequest appointmentRequest) {
        Doctor doctor = doctorRepository.findById(appointmentRequest.getDoctorId()).orElseThrow(
                () -> new ResourceNotFoundException("Doctor not found!")
        );

        Patient patient = patientRepository.findById(appointmentRequest.getPatientId()).orElseThrow(
                () -> new ResourceNotFoundException("Patient not found!")
        );

        Appointment appointment = Appointment.builder()
                .doctor(doctor)
                .patient(patient)
                .appointmentTime(appointmentRequest.getAppointmentTime())
                .status(AppointmentStatus.BOOKED)
                .build();

        appointmentRepository.save(appointment);

        return mapToDTO(appointment);
    }

    @Override
    public Page<AppointmentResponse> getAllAppointments(int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size);

        return appointmentRepository.findAll(pageRequest).map(this::mapToDTO);
    }

    @Override
    public AppointmentResponse cancelAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(
                () -> new ResourceNotFoundException("Appointment not found!")
        );

        appointment.setStatus(AppointmentStatus.CANCELLED);

        appointmentRepository.save(appointment);

        return mapToDTO(appointment);
    }

    @Override
    public Page<AppointmentResponse> getAppointmentsByDoctor(Long doctorId, int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        return appointmentRepository.findByDoctorId(doctorId, pageRequest).map(this::mapToDTO);
    }

    @Override
    public Page<AppointmentResponse> getAppointmentsByPatient(Long patientId, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return appointmentRepository
                .findByPatientId(patientId, pageable)
                .map(this::mapToDTO);
    }

    private AppointmentResponse mapToDTO(Appointment appointment) {
        return AppointmentResponse.builder()
                .id(appointment.getId())
                .doctorName(appointment.getDoctor().getUser().getName())
                .patientName(appointment.getPatient().getUser().getName())
                .appointmentTime(appointment.getAppointmentTime())
                .appointmentStatus(appointment.getStatus())
                .build();
    }
}
