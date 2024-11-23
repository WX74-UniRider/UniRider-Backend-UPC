package com.unirider.management.domain.model.aggregates;

import com.unirider.iam.domain.model.aggregates.User;
import com.unirider.management.domain.model.commands.CreateTripCommand;
import com.unirider.management.domain.model.commands.UpdateTripCommand;
import com.unirider.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Trip extends AuditableAbstractAggregateRoot<Trip> {

    private String destination;

    @LastModifiedDate
    private String departureTime;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private User driver;

    @Enumerated(EnumType.STRING)
    private TripStatus status;

    private Double price;

    public Trip() {}

    public Trip(CreateTripCommand command, User driver) {
        this.destination = command.destination();
        this.driver = driver;
        this.status = command.status();
        this.price = command.price();
        this.departureTime = command.departureTime();
    }

    public void updateTrip(UpdateTripCommand command, User driver) {
        this.destination = command.destination();
        this.driver = driver;
        this.status = command.status();
        this.price = command.price();
        this.departureTime = command.departureTime();
    }
    public void setStatus(String status) {
        try {
            this.status = TripStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid TripStatus value: " + status);
        }
    }
}