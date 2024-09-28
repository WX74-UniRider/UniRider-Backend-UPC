package com.unirider.user.infrastructure.persistence.jpa.repositories;

import com.unirider.user.domain.model.aggregates.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
