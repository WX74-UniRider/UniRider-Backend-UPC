package com.unirider.iam.interfaces.rest.transform;

import com.unirider.iam.domain.model.commands.SignUpDriverCommand;
import com.unirider.iam.interfaces.rest.resources.SignUpDriverResource;

public class SignUpDriverCommandFromResourceAssembler {
    public static SignUpDriverCommand toCommandFromResource(SignUpDriverResource resource) {
        return new SignUpDriverCommand(
                resource.username(),
                resource.password(),
                resource.firstName(),
                resource.lastName(),
                resource.vehiclePlate()
        );
    }
}
