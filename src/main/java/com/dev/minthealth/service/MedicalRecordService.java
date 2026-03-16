package com.dev.minthealth.service;

import com.dev.minthealth.dto.MedicalRecordRequest;
import com.dev.minthealth.dto.MedicalRecordResponse;

public interface MedicalRecordService {

    MedicalRecordResponse createRecord(MedicalRecordRequest request);

    MedicalRecordResponse getRecordByAppointment(Long appointmentId);

}