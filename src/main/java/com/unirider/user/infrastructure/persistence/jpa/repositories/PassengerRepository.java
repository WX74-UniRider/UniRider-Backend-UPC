package com.unirider.user.infrastructure.persistence.jpa.repositories;

import com.unirider.user.domain.model.aggregates.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository  extends JpaRepository<Passenger, Long> {
}
