package com.unirider.user.application.internal.profileServiceImpl;

import com.unirider.management.domain.model.commands.UpdateIdCardUrlCommand;
import com.unirider.user.domain.model.aggregates.Driver;
import com.unirider.user.domain.model.aggregates.Passenger;
import com.unirider.user.domain.model.commands.UpdateDriverCommand;
import com.unirider.user.domain.model.commands.UpdatePassengerCommand;
import com.unirider.user.domain.model.queries.GetAllDriversQuery;
import com.unirider.user.domain.model.queries.GetDriverByIdQuery;
import com.unirider.user.domain.services.ProfileService;
import com.unirider.user.infrastructure.persistence.jpa.repositories.DriverRepository;
import com.unirider.user.infrastructure.persistence.jpa.repositories.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final PassengerRepository passengerRepository;
    private final DriverRepository driverRepository;

    public ProfileServiceImpl(PassengerRepository passengerRepository, DriverRepository driverRepository) {
        this.passengerRepository = passengerRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    public Optional<Passenger> handle(UpdatePassengerCommand command) {
        return passengerRepository.findById(command.passengerId()).map(passenger -> {
            passenger.setUser(command.user());
            passenger.setFirstName(command.firstName());
            passenger.setLastName(command.lastName());
            passenger.setPreferences(command.preferences());
            passenger.setPhoneNumber(command.phoneNumber());
            passenger.setAddress(command.address());
            passenger.setIdCardUrl(command.idCardUrl());
            return passengerRepository.save(passenger);
        });
    }

    @Override
    public Optional<Driver> handle(UpdateDriverCommand command) {
        return driverRepository.findById(command.driverId()).map(driver -> {
            driver.setUser(command.user());
            driver.setFirstName(command.firstName());
            driver.setLastName(command.lastName());
            driver.setPhoneNumber(command.phoneNumber());
            driver.setVehiclePlate(command.vehiclePlate());
            driver.setInsurance(command.insurance());
            driver.setVehicleModel(command.vehicleModel());
            driver.setDestino(command.destino());
            return driverRepository.save(driver);
        });
    }

    @Override
    public Optional<Driver> handle(GetDriverByIdQuery query) {
        return driverRepository.findById(query.driverId());
    }

    @Override
    public List<Driver> handle(GetAllDriversQuery query) {
        return driverRepository.findAll();
    }


    public List<Driver> getDriversByDestination(String destination) {
        return driverRepository.findByDestino(destination);
    }

    @Override
    public void updateIdCardUrl(UpdateIdCardUrlCommand command) {
        Passenger passenger = passengerRepository.findById(command.passengerId()).orElseThrow();
        passenger.setIdCardUrl(command.idCardUrl());
        passengerRepository.save(passenger);
    }
}