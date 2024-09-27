package com.unirider.iam.domain.model.commands;

public record SignUpPassengerCommand(
        String username ,
        String password ,
        String firstName,
        String lastName)
{}
