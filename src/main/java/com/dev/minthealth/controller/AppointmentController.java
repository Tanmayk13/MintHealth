package com.dev.minthealth.controller;

import com.dev.minthealth.dto.AppointmentRequest;
import com.dev.minthealth.dto.AppointmentResponse;
import com.dev.minthealth.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/appointments")
@Tag(name = "Appointments", description = "APIs for Appointment Management")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping
    @PreAuthorize("hasAnyRole('DOCTOR', 'ADMIN')")
    @Operation(summary = "Get all appointments")
    public ResponseEntity<Page<AppointmentResponse>> getAllAppointments(
            @Parameter(description = "Page number", example = "0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size", example = "10")
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(appointmentService.getAllAppointments(page, size));
    }

    @PostMapping
    @PreAuthorize("hasRole('PATIENT')")
    @Operation(summary = "Book an appointment")
    public ResponseEntity<AppointmentResponse> bookAppointment(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Appointment Booking Request")
            @Valid @RequestBody AppointmentRequest appointmentRequest) {
        return ResponseEntity.ok(appointmentService.bookAppointment(appointmentRequest));
    }

    @PutMapping("/{appointmentId}/cancel")
    @Operation(summary = "Cancel an appointment")
    @PreAuthorize("hasRole('PATIENT')")
    public AppointmentResponse cancelAppointment(@PathVariable Long appointmentId) {
        return appointmentService.cancelAppointment(appointmentId);
    }

    @GetMapping("/doctor/{doctorId}")
    @PreAuthorize("hasRole('DOCTOR')")
    @Operation(summary = "Get all appointments by specific doctor")
    public Page<AppointmentResponse> getDoctorAppointments(@Parameter(description = "Doctor ID", example = "1") @PathVariable Long doctorId,
                                                           @Parameter(description = "Page number", example = "0")
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @Parameter(description = "Page size", example = "10")
                                                           @RequestParam(defaultValue = "10") int size) {
        return appointmentService.getAppointmentsByDoctor(doctorId, page, size);
    }

    @GetMapping("/patient/{patientId}")
    @PreAuthorize("hasRole('PATIENT')")
    @Operation(summary = "Get all appointments by specific patient")
    public Page<AppointmentResponse> getPatientAppointments(
            @Parameter(description = "Patient ID", example = "1")
            @PathVariable Long patientId,
            @Parameter(description = "Page number", example = "0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size", example = "0")
            @RequestParam(defaultValue = "10") int size) {

        return appointmentService.getAppointmentsByPatient(patientId, page, size);
    }
}
