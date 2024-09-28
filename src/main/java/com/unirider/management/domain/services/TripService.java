package com.unirider.management.domain.services;

import com.unirider.management.domain.model.aggregates.Trip;
import com.unirider.management.domain.model.commands.CreateTripCommand;
import com.unirider.management.domain.model.commands.UpdateTripCommand;
import com.unirider.management.domain.model.queries.DeleteTripCommand;
import com.unirider.management.domain.model.queries.GetAllTripsQuery;
import com.unirider.management.domain.model.queries.GetTripByIdQuery;

import java.util.List;
import java.util.Optional;

public interface TripService {
    Optional<Trip> handle(CreateTripCommand command);

    Optional<Trip> handle(UpdateTripCommand command);

    void handle(DeleteTripCommand command);

    Optional<Trip> handle(GetTripByIdQuery query);

    List<Trip> handle(GetAllTripsQuery query);
}
