package com.unirider.management.application.internal.tripServiceImpl;

import com.unirider.iam.domain.model.aggregates.User;
import com.unirider.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.unirider.management.domain.model.aggregates.Trip;
import com.unirider.management.domain.model.aggregates.TripStatus;
import com.unirider.management.domain.model.commands.CreateTripCommand;
import com.unirider.management.domain.model.commands.UpdateTripCommand;
import com.unirider.management.domain.model.queries.DeleteTripCommand;
import com.unirider.management.domain.model.queries.GetAllTripsQuery;
import com.unirider.management.domain.model.queries.GetTripByIdQuery;
import com.unirider.management.domain.services.TripService;
import com.unirider.management.infrastructure.persistence.jpa.repositories.TripRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;
    private final UserRepository userRepository;

    public TripServiceImpl(TripRepository tripRepository, UserRepository userRepository) {
        this.tripRepository = tripRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Trip> handle(CreateTripCommand command) {
        User driver = userRepository.findById(command.driverId()).orElseThrow();
        Trip trip = new Trip(command, driver);
        return Optional.of(tripRepository.save(trip));
    }

    @Override
    public Optional<Trip> handle(UpdateTripCommand command) {
        return tripRepository.findById(command.tripId()).map(trip -> {
            User driver = userRepository.findById(command.driverId()).orElseThrow();
            trip.updateTrip(command, driver);
            return tripRepository.save(trip);
        });
    }

    @Override
    public void handle(DeleteTripCommand command) {
        tripRepository.deleteById(command.tripId());
    }

    @Override
    public Optional<Trip> handle(GetTripByIdQuery query) {
        return tripRepository.findById(query.tripId());
    }

    @Override
    public List<Trip> handle(GetAllTripsQuery query) {
        return tripRepository.findAll();
    }

    @Override
    public List<Trip> getTripsByDriverId(Long driverId) {
        return tripRepository.findByDriverId(driverId);
    }

    @Override
    public List<Trip> getTripsByStatus(TripStatus status) {
        return tripRepository.findByStatus(status);
    }
}