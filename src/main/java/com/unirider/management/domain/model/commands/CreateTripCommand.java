package com.unirider.management.domain.model.commands;

import com.unirider.iam.domain.model.aggregates.User;

public record CreateTripCommand(
        String destination,
        Long driverId,
        Long passengerId,
        String status,
        Double price
) {
}
