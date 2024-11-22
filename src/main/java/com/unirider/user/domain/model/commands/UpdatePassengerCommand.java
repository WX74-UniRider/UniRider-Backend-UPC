package com.unirider.user.domain.model.commands;

import com.unirider.iam.domain.model.aggregates.User;

public record UpdatePassengerCommand(
        Long passengerId,
        User user,
        String firstName,
        String lastName,
        String preferences,
        String phoneNumber,
        String address,
        String idCardUrl
) {
}