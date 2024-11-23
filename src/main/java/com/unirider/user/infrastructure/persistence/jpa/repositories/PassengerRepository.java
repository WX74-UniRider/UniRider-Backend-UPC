package com.unirider.user.infrastructure.persistence.jpa.repositories;

import com.unirider.user.domain.model.aggregates.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PassengerRepository  extends JpaRepository<Passenger, Long> {
    Optional<Passenger> findByUser_Id(Long userId);

}
