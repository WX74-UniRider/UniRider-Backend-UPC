package com.unirider.user.domain.model.commands;

public record UpdateDriverCommand(
        Long driverId,
        String firstName,
        String lastName,
        String phoneNumber,
        String vehiclePlate,
        String insurance,
        String vehicleModel
) {
}