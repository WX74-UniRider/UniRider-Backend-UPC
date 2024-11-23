package com.unirider.user.domain.model.commands;

public record UpdateIdCardUrlCommand(Long passengerId, String idCardUrl) {
}
