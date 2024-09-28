package com.unirider.user.domain.model.commands;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateRatingCommand(
        @NotNull Long tripId,
        @NotNull Long passengerId,
        @NotNull Long driverId,
        @Min(1) @Max(5) int score,  // La puntuación debe estar entre 1 y 5
        String comment  // Comentario opcional
) {
}
