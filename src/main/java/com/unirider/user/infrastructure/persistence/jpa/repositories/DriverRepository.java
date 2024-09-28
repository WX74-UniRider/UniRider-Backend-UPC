package com.unirider.user.infrastructure.persistence.jpa.repositories;

import com.unirider.user.domain.model.aggregates.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    List<Driver> findByDestino(String destino);

}
