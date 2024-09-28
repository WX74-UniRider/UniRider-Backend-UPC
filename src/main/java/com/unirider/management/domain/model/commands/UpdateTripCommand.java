package com.unirider.management.domain.model.commands;

import com.unirider.iam.domain.model.aggregates.User;

public record UpdateTripCommand(
        Long tripId,
        String destination,
        User driverId,
        User passengerId,
        String status
) {
}