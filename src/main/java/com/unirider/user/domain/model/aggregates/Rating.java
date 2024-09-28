package com.unirider.user.domain.model.aggregates;


import com.unirider.iam.domain.model.aggregates.User;
import com.unirider.management.domain.model.aggregates.Trip;
import com.unirider.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.unirider.user.domain.model.commands.CreateRatingCommand;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Rating extends AuditableAbstractAggregateRoot<Rating> {

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private User driver;

    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private User passenger;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    private int score;  // Puntuación, por ejemplo, de 1 a 5
    private String comment;  // Comentario opcional

    public Rating() {
    }

    public Rating(CreateRatingCommand command, User driver, User passenger, Trip trip) {
        this.driver = driver;
        this.passenger = passenger;
        this.trip = trip;
        this.score = command.score();
        this.comment = command.comment();
    }
}
