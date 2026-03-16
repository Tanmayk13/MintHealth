package com.dev.minthealth.service.impl;

import com.dev.minthealth.dto.DoctorResponse;
import com.dev.minthealth.entity.Doctor;
import com.dev.minthealth.exception.ResourceNotFoundException;
import com.dev.minthealth.repository.DoctorRepository;
import com.dev.minthealth.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    @Override
    public Page<DoctorResponse> getAllDoctors(int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size);

        return doctorRepository.findAll(pageRequest).map(this::mapToDTO);
    }

    @Override
    public DoctorResponse getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Doctor not Found!")
        );

        return mapToDTO(doctor);
    }

    private DoctorResponse mapToDTO(Doctor doctor) {
        return DoctorResponse.builder()
                .id(doctor.getId())
                .name(doctor.getUser().getName())
                .email(doctor.getUser().getEmail())
                .specialization(doctor.getSpecialization())
                .experience(doctor.getExperience())
                .build();
    }
}
