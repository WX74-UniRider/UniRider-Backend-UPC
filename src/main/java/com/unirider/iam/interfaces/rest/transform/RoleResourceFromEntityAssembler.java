package com.unirider.iam.interfaces.rest.transform;

import com.unirider.iam.domain.model.entities.Role;
import com.unirider.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}