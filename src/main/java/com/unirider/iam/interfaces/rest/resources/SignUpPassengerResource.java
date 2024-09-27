package com.unirider.iam.interfaces.rest.resources;

public record SignUpPassengerResource(
        String username,
        String password,
        String firstName,
        String lastName
) {
}
