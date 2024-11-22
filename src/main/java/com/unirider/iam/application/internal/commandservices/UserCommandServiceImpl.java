package com.unirider.iam.application.internal.commandservices;

import com.unirider.iam.application.internal.outboundservices.hashing.HashingService;
import com.unirider.iam.application.internal.outboundservices.tokens.TokenService;
import com.unirider.iam.domain.model.aggregates.User;
import com.unirider.iam.domain.model.commands.SignInCommand;
import com.unirider.iam.domain.model.commands.SignUpDriverCommand;
import com.unirider.iam.domain.model.commands.SignUpPassengerCommand;
import com.unirider.iam.domain.model.entities.Role;
import com.unirider.iam.domain.model.valueobjects.Roles;
import com.unirider.iam.domain.services.UserCommandService;
import com.unirider.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.unirider.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.unirider.user.domain.model.aggregates.Driver;
import com.unirider.user.domain.model.aggregates.Passenger;
import com.unirider.user.infrastructure.persistence.jpa.repositories.DriverRepository;
import com.unirider.user.infrastructure.persistence.jpa.repositories.PassengerRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    private final DriverRepository driverRepository;
    private final PassengerRepository passengerRepository;

    public UserCommandServiceImpl(UserRepository userRepository, RoleRepository roleRepository, HashingService hashingService, TokenService tokenService, DriverRepository driverRepository, PassengerRepository passengerRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.driverRepository = driverRepository;
        this.passengerRepository = passengerRepository;
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.username());
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        if (!hashingService.matches(command.password(), user.get().getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        var token = tokenService.generateToken(user.get().getUsername());
        return Optional.of(ImmutablePair.of(user.get(), token));
    }

    @Override
    public Optional<User> handle(SignUpPassengerCommand command) {
        if (userRepository.existsByUsername(command.username())) {
            throw new RuntimeException("Username already exists");
        }

        // Buscar el rol de pasajero en el repositorio de roles
        Role passengerRole = roleRepository.findByName(Roles.valueOf("ROLE_PASAJERO"))
                .orElseThrow(() -> new RuntimeException("Passenger role not found"));

        // Crear una lista con el rol de pasajero
        List<Role> roles = List.of(passengerRole);

        // Crear el usuario con el rol de pasajero
        var user = new User(command.username(), hashingService.encode(command.password()), roles);
        userRepository.save(user);

        Passenger passenger = new Passenger(
                user,
                command.firstName(),
                command.lastName(),
                "No preferences provided.",
                "999 999 999",
                "No address provided.",
                "No id card provided."
        );
        passengerRepository.save(passenger);

        return Optional.of(user);
    }

    @Override
    public Optional<User> handle(SignUpDriverCommand command) {
        if (userRepository.existsByUsername(command.username())) {
            throw new RuntimeException("Username already exists");
        }

        // Buscar el rol de conductor en el repositorio de roles
        Role driverRole = roleRepository.findByName(Roles.valueOf("ROLE_CONDUCTOR"))
                .orElseThrow(() -> new RuntimeException("Driver role not found"));

        // Crear una lista con el rol de conductor
        List<Role> roles = List.of(driverRole);

        // Crear el usuario con el rol de conductor
        var user = new User(command.username(), hashingService.encode(command.password()), roles);
        userRepository.save(user);

        Driver driver = new Driver(
                user,
                command.firstName(),
                command.lastName(),
                "999 999 999",
                command.vehiclePlate(),
                "No insurance provided",
                "No vehicle model provided"
        );
        driverRepository.save(driver);

        return Optional.of(user);
    }
}
