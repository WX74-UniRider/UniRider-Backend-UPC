package com.unirider.management.interfaces.rest;

import com.unirider.management.domain.model.aggregates.Trip;
import com.unirider.management.domain.model.aggregates.TripStatus;
import com.unirider.management.domain.model.commands.CreateTripCommand;
import com.unirider.management.domain.model.commands.UpdateTripCommand;
import com.unirider.management.domain.model.queries.DeleteTripCommand;
import com.unirider.management.domain.model.queries.GetAllTripsQuery;
import com.unirider.management.domain.model.queries.GetTripByIdQuery;
import com.unirider.management.domain.services.TripService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trips")
@Tag(name = "Trips", description = "Trip Management Endpoints")
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping
    public ResponseEntity<Trip> createTrip(@RequestBody CreateTripCommand command) {
        return ResponseEntity.ok(tripService.handle(command).orElseThrow());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trip> updateTrip(@PathVariable Long id, @RequestBody UpdateTripCommand command) {
        return tripService.handle(command)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long id) {
        tripService.handle(new DeleteTripCommand(id));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripById(@PathVariable Long id) {
        return tripService.handle(new GetTripByIdQuery(id))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Trip>> getAllTrips() {
        return ResponseEntity.ok(tripService.handle(new GetAllTripsQuery()));
    }
    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<Trip>> getTripsByDriverId(@PathVariable Long driverId) {
        return ResponseEntity.ok(tripService.getTripsByDriverId(driverId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Trip>> getTripsByStatus(@PathVariable TripStatus status) {
        return ResponseEntity.ok(tripService.getTripsByStatus(status));
    }
}