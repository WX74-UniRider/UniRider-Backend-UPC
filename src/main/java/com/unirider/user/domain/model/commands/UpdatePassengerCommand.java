package com.unirider.user.domain.model.commands;

public record UpdatePassengerCommand(
        Long passengerId,
        String firstName,
        String lastName,
        String preferences,
        String phoneNumber,
        String address
) {
}