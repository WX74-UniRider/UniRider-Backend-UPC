package com.unirider.user.domain.services;

import com.unirider.management.domain.model.commands.UpdateIdCardUrlCommand;
import com.unirider.user.domain.model.aggregates.Driver;
import com.unirider.user.domain.model.aggregates.Passenger;
import com.unirider.user.domain.model.commands.UpdateDriverCommand;
import com.unirider.user.domain.model.commands.UpdatePassengerCommand;
import com.unirider.user.domain.model.queries.GetAllDriversQuery;
import com.unirider.user.domain.model.queries.GetDriverByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ProfileService {
    Optional<Passenger>handle(UpdatePassengerCommand command);
    Optional<Driver> handle(UpdateDriverCommand command);
    Optional<Driver>handle(GetDriverByIdQuery query);
    List<Driver> handle(GetAllDriversQuery query);
    List<Driver> getDriversByDestination(String destination);
    void updateIdCardUrl(UpdateIdCardUrlCommand command);

}
