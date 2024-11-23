package com.unirider.management.domain.model.commands;

import java.time.LocalDateTime;

public record CreateTripCommand(
        String destination,
        Long driverId,
        String status,
        Double price,
        LocalDateTime departureTime
) {
}