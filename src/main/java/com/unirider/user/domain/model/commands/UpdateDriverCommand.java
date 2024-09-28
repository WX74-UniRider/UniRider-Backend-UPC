package com.unirider.user.domain.model.commands;

import com.unirider.iam.domain.model.aggregates.User;

public record UpdateDriverCommand(
        Long driverId,
        User user,
        String firstName,
        String lastName,
        String phoneNumber,
        String vehiclePlate,
        String insurance,
        String vehicleModel,
        String destino
) {
}