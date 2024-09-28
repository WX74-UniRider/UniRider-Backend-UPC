package com.unirider.user.domain.services;


import com.unirider.iam.domain.model.aggregates.User;
import com.unirider.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.unirider.management.domain.model.aggregates.Trip;

import com.unirider.management.infrastructure.persistence.jpa.repositories.TripRepository;
import com.unirider.user.domain.model.aggregates.Rating;
import com.unirider.user.domain.model.commands.CreateRatingCommand;
import com.unirider.user.infrastructure.persistence.jpa.repositories.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingCommandService {

    private final RatingRepository ratingRepository;
    private final TripRepository tripRepository;
    private final UserRepository userRepository;

    public RatingCommandService(RatingRepository ratingRepository, TripRepository tripRepository, UserRepository userRepository) {
        this.ratingRepository = ratingRepository;
        this.tripRepository = tripRepository;
        this.userRepository = userRepository;
    }

    public Optional<Rating> createRating(CreateRatingCommand command) {
        Optional<Trip> trip = tripRepository.findById(command.tripId());
        Optional<User> driver = userRepository.findById(command.driverId());
        Optional<User> passenger = userRepository.findById(command.passengerId());

        if (trip.isPresent() && driver.isPresent() && passenger.isPresent()) {
            Rating rating = new Rating(command, driver.get(), passenger.get(), trip.get());
            return Optional.of(ratingRepository.save(rating));
        } else {
            return Optional.empty(); // Si falta algún dato, retorna vacío
        }
    }
}
