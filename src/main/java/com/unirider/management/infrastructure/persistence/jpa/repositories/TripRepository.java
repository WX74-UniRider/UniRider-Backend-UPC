package com.unirider.management.infrastructure.persistence.jpa.repositories;

import com.unirider.management.domain.model.aggregates.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
}