package com.unirider.management.domain.model.aggregates;

import com.unirider.iam.domain.model.aggregates.User;
import com.unirider.management.domain.model.commands.CreateReservationCommand;
import com.unirider.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Setter
@Getter
public class Reservation extends AuditableAbstractAggregateRoot<Reservation> {

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private User passenger;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = true) // Driver puede ser opcional si aún no se ha asignado
    private User driver;

    private LocalDateTime reservationTime;
    private LocalDateTime tripStartTime;
    private String destination;
    private String status;

    public Reservation() {
    }


    public Reservation(CreateReservationCommand command, Trip trip, User passenger, User driver) {
        this.trip = trip;
        this.passenger = passenger;
        this.driver = driver;
        this.reservationTime = LocalDateTime.now();
        this.tripStartTime = command.tripStartTime();
        this.destination = command.destination();
        this.status = "PENDING";
    }
}
