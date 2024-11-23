package com.unirider.management.domain.model.commands;

import com.unirider.management.domain.model.aggregates.TripStatus;

import java.time.LocalDateTime;

public record UpdateTripCommand(
        Long tripId,
        String destination,
        Long driverId,
        TripStatus status,
        Double price,
        String departureTime
) {
}