package com.unirider.iam.domain.model.commands;



public record SignUpDriverCommand(
        String username ,
        String password,
        String firstName,
        String lastName,
        String vehiclePlate
) {
}
