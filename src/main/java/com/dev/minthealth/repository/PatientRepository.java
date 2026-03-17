package com.dev.minthealth.repository;

import com.dev.minthealth.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByUserEmail(String email);

    boolean existsByIdAndUserEmail(Long id, String email);
}
