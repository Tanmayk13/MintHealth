package com.dev.minthealth.service.impl;

import com.dev.minthealth.dto.MedicalRecordRequest;
import com.dev.minthealth.dto.MedicalRecordResponse;
import com.dev.minthealth.entity.Appointment;
import com.dev.minthealth.entity.MedicalRecord;
import com.dev.minthealth.exception.ResourceNotFoundException;
import com.dev.minthealth.repository.AppointmentRepository;
import com.dev.minthealth.repository.MedicalRecordRepository;
import com.dev.minthealth.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final AppointmentRepository appointmentRepository;

    @Override
    public MedicalRecordResponse createRecord(MedicalRecordRequest request) {
        Appointment appointment = appointmentRepository.findById(request.getAppointmentId()).orElseThrow(
                () -> new ResourceNotFoundException("No appointment found!")
        );

        MedicalRecord record = MedicalRecord.builder()
                .diagnosis(request.getDiagnosis())
                .prescription(request.getPrescription())
                .appointment(appointment)
                .build();

        medicalRecordRepository.save(record);

        return mapToDTO(record);
    }

    @Override
    public MedicalRecordResponse getRecordByAppointment(Long appointmentId) {
        MedicalRecord record = medicalRecordRepository.findAll()
                .stream()
                .filter(r -> r.getAppointment().getId().equals(appointmentId))
                .findFirst()
                .orElseThrow(
                        () -> new ResourceNotFoundException("Record not found!")
                );

        return mapToDTO(record);
    }

    private MedicalRecordResponse mapToDTO(MedicalRecord record) {
        return MedicalRecordResponse.builder()
                .id(record.getId())
                .doctorName(record.getAppointment().getDoctor().getUser().getName())
                .patientName(record.getAppointment().getPatient().getUser().getName())
                .diagnosis(record.getDiagnosis())
                .prescription(record.getPrescription())
                .build();
    }
}
