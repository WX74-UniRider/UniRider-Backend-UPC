package com.unirider.iam.interfaces.rest.transform;

import com.unirider.iam.domain.model.commands.SignUpPassengerCommand;
import com.unirider.iam.interfaces.rest.resources.SignUpPassengerResource;

public class SignUpPassengerCommandFromResourceAssembler {
    public static SignUpPassengerCommand toCommandFromResource(SignUpPassengerResource resource) {
        return new SignUpPassengerCommand(
                resource.username(),
                resource.password(),
                resource.firstName(),
                resource.lastName()
        );
    }
}
