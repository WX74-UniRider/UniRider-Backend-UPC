package com.unirider.iam.domain.model.queries;


import com.unirider.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles name) {
}
