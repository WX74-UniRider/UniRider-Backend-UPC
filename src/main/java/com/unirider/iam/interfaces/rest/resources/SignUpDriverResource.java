package com.unirider.iam.interfaces.rest.resources;

public record SignUpDriverResource(
        String username,
        String password,
        String firstName,
        String lastName,
        String vehiclePlate
) {
}
