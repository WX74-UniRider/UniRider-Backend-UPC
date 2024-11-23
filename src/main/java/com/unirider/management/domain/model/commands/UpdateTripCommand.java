package com.unirider.management.domain.model.commands;

import java.time.LocalDateTime;

public record UpdateTripCommand(
        Long tripId,
        String destination,
        Long driverId,
        String status,
        Double price,
        LocalDateTime departureTime
) {
}