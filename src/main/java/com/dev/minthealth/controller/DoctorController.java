package com.dev.minthealth.controller;

import com.dev.minthealth.dto.DoctorResponse;
import com.dev.minthealth.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Doctors", description = "APIs for accessing Doctor information")
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("/doctors")
    @PreAuthorize("hasAnyRole('ADMIN', 'PATIENT')")
    @Operation(summary = "Get all doctors", description = "Get a list of all available doctors")
    public ResponseEntity<Page<DoctorResponse>> getAllDoctors(
            @Parameter(description = "Page number", example = "0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size", example = "10")
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(doctorService.getAllDoctors(page, size));
    }

    @GetMapping("/doctors/{id}")
    @Operation(summary = "Get a doctor by ID", description = "Get an information about specific doctor by ID")

    public ResponseEntity<DoctorResponse> getDoctorById(@Parameter(description = "Doctor ID", example = "1")
                                                        @PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }
}
