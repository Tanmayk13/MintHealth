package com.dev.minthealth.controller;

import com.dev.minthealth.dto.MedicalRecordRequest;
import com.dev.minthealth.dto.MedicalRecordResponse;
import com.dev.minthealth.service.MedicalRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/records")
@Tag(name = "Medical Records", description = "APIs for accessing the Medical records")
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    @GetMapping("/{appointmentId}")
    @Operation(summary = "Get all Medical records")
    @PreAuthorize("hasAnyRole('DOCTOR', 'ADMIN') or (hasRole('PATIENT') and @medicalRecordSecurity.isOwner(authentication, #appointmentId))")
    public ResponseEntity<MedicalRecordResponse> getMedicalRecord(@Parameter(description = "Appointment ID", example = "1")
                                                                  @PathVariable Long appointmentId) {
        return ResponseEntity.ok(medicalRecordService.getRecordByAppointment(appointmentId));
    }

    @PostMapping
    @PreAuthorize("hasRole('DOCTOR')")
    @Operation(summary = "Add a Medical record")
    public ResponseEntity<MedicalRecordResponse> createMedicalRecord(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Medical record addition request")
            @Valid @RequestBody MedicalRecordRequest request) {
        return ResponseEntity.ok(medicalRecordService.createRecord(request));
    }
}
