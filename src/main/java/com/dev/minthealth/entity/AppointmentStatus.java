package com.dev.minthealth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


public enum AppointmentStatus {
    BOOKED,
    CANCELLED,
    COMPLETED
}