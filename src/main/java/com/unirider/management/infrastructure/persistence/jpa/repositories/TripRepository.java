package com.unirider.management.infrastructure.persistence.jpa.repositories;

import com.unirider.management.domain.model.aggregates.Trip;
import com.unirider.management.domain.model.aggregates.TripStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByDriverId(Long driverId);
    List<Trip> findByStatus(TripStatus status);

}