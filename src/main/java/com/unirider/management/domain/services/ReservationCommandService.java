package com.unirider.management.domain.services;


import com.unirider.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.unirider.management.domain.model.aggregates.Reservation;
import com.unirider.management.domain.model.aggregates.Trip;
import com.unirider.iam.domain.model.aggregates.User;
import com.unirider.management.domain.model.commands.CreateReservationCommand;

import com.unirider.management.domain.model.commands.DeleteReservationCommand;
import com.unirider.management.infrastructure.persistence.jpa.repositories.ReservationRepository;
import com.unirider.management.infrastructure.persistence.jpa.repositories.TripRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ReservationCommandService {

    private final ReservationRepository reservationRepository;
    private final TripRepository tripRepository;
    private final UserRepository userRepository;

    public ReservationCommandService(ReservationRepository reservationRepository, TripRepository tripRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.tripRepository = tripRepository;
        this.userRepository = userRepository;
    }

    /**
     * Crear una reserva.
     */
    public Optional<Reservation> createReservation(CreateReservationCommand command) {
        // Buscar el viaje y el pasajero por sus IDs
        Optional<Trip> trip = tripRepository.findById(command.tripId());
        Optional<User> passenger = userRepository.findById(command.passengerId());

        // Verificar si ambos existen
        if (trip.isEmpty() || passenger.isEmpty()) {
            return Optional.empty(); // Si el viaje o pasajero no existen, retorna vacío
        }

        // Buscar al conductor si se ha proporcionado su ID
        User driver = null;
        if (command.driverId() != null) {
            Optional<User> optionalDriver = userRepository.findById(command.driverId());
            if (optionalDriver.isPresent()) {
                driver = optionalDriver.get();
            }
        }

        // Crear la nueva reserva
        Reservation reservation = new Reservation(command, trip.get(), passenger.get(), driver);

        // Guardar la reserva en la base de datos
        Reservation savedReservation = reservationRepository.save(reservation);

        return Optional.of(savedReservation);
    }

    /**
     * Eliminar una reserva.
     */
    public boolean deleteReservation(DeleteReservationCommand command) {
        // Buscar la reserva por su ID
        Optional<Reservation> reservation = reservationRepository.findById(command.reservationId());

        if (reservation.isPresent()) {
            // Si la reserva existe, eliminarla
            reservationRepository.delete(reservation.get());
            return true;
        } else {
            // Si no existe, retornar falso
            return false;
        }
    }
}
