package com.dev.minthealth.service;

import com.dev.minthealth.dto.DoctorResponse;
import com.dev.minthealth.entity.Doctor;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DoctorService {

    Page<DoctorResponse> getAllDoctors(int page, int size);

    DoctorResponse getDoctorById(Long id);
}
