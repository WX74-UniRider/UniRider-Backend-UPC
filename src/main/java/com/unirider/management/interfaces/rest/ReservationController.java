package com.unirider.management.interfaces.rest;


import com.unirider.management.domain.model.aggregates.Reservation;
import com.unirider.management.domain.model.commands.CreateReservationCommand;
import com.unirider.management.domain.model.commands.DeleteReservationCommand;
import com.unirider.management.domain.services.ReservationCommandService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reservations")
@Tag(name = "Reservations", description = "Reservations Management Endpoints")
public class ReservationController {

    private final ReservationCommandService reservationCommandService;

    public ReservationController(ReservationCommandService reservationCommandService) {
        this.reservationCommandService = reservationCommandService;
    }

    /**
     * Endpoint para crear una nueva reserva.
     *
     * @param command El comando que contiene los datos necesarios para crear la reserva.
     * @return ResponseEntity con la información de la reserva creada o un error si no se pudo crear.
     */
    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody CreateReservationCommand command) {
        Optional<Reservation> reservation = reservationCommandService.createReservation(command);

        if (reservation.isPresent()) {
            return new ResponseEntity<>(reservation.get(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Endpoint para eliminar una reserva existente.
     *
     * @param reservationId El ID de la reserva que se desea eliminar.
     * @return ResponseEntity con el estado de la operación.
     */
    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long reservationId) {
        DeleteReservationCommand command = new DeleteReservationCommand(reservationId);
        boolean deleted = reservationCommandService.deleteReservation(command);

        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
