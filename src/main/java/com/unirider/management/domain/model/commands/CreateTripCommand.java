package com.unirider.management.domain.model.commands;

import com.unirider.management.domain.model.aggregates.TripStatus;

import java.time.LocalDateTime;

public record CreateTripCommand(
        String destination,
        Long driverId,
        TripStatus status,
        Double price,
        String departureTime
) {
}