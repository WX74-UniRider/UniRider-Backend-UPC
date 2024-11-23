package com.unirider.user.interfaces.rest;

import com.unirider.user.domain.model.aggregates.Driver;
import com.unirider.user.domain.model.aggregates.Passenger;
import com.unirider.user.domain.model.commands.UpdateDriverCommand;
import com.unirider.user.domain.model.commands.UpdateIdCardUrlCommand;
import com.unirider.user.domain.model.commands.UpdatePassengerCommand;
import com.unirider.user.domain.model.queries.GetAllDriversQuery;
import com.unirider.user.domain.model.queries.GetDriverByIdQuery;
import com.unirider.user.domain.services.ProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/profile")
@Tag(name = "Profile", description = "Profile Management Endpoints")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }


    @PutMapping("/passenger")
    public ResponseEntity<Passenger> updatePassenger(@RequestBody UpdatePassengerCommand command) {
        return profileService.handle(command)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/driver")
    public ResponseEntity<Driver> updateDriver(@RequestBody UpdateDriverCommand command) {
        return profileService.handle(command)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/driver/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable Long id) {
        return profileService.handle(new GetDriverByIdQuery(id))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/drivers")
    public ResponseEntity<List<Driver>> getAllDrivers() {
        return ResponseEntity.ok(profileService.handle(new GetAllDriversQuery()));
    }

    @GetMapping("/drivers/destination")
    public ResponseEntity<List<Driver>> getDriversByDestination(@RequestParam String destination) {
        List<Driver> drivers = profileService.getDriversByDestination(destination);
        return ResponseEntity.ok(drivers);
    }
    @PatchMapping("/{id}/idCardUrl")
    public ResponseEntity<Void> updateIdCardUrl(@PathVariable Long id, @RequestBody String idCardUrl) {
        profileService.updateIdCardUrl(new UpdateIdCardUrlCommand(id, idCardUrl));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/passengers/user/{userId}")
    public ResponseEntity<Passenger> getPassengerByUserId(@PathVariable Long userId) {
        return profileService.getPassengerByUserId(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}