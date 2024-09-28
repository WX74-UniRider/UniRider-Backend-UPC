package com.unirider.management.domain.model.commands;

import java.time.LocalDateTime;

public record CreateReservationCommand(
        Long tripId,
        Long passengerId,
        Long driverId,  // Opcional, puede no estar asignado en el momento de la reserva
        LocalDateTime tripStartTime,
        String destination
) {
}
