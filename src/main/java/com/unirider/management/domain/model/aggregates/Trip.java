package com.unirider.management.domain.model.aggregates;

import com.unirider.iam.domain.model.aggregates.User;
import com.unirider.management.domain.model.commands.CreateTripCommand;
import com.unirider.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
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
    private LocalDateTime departureTime;

    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private User passenger;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private User driver;

    private String status;

    private Double price;

    public Trip(){}

    public Trip(CreateTripCommand command , User driver, User passenger) {
        this.destination = command.destination();
        this.driver = driver;
        this.passenger = passenger;
        this.status = command.status();
        this.price = command.price();
    }
}
