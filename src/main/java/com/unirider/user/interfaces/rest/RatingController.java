package com.unirider.user.interfaces.rest;



import com.unirider.user.domain.model.aggregates.Rating;
import com.unirider.user.domain.model.commands.CreateRatingCommand;
import com.unirider.user.domain.services.RatingCommandService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/ratings")
@Tag(name = "Ratings", description = "Ratings Management Endpoints")
public class RatingController {

    private final RatingCommandService ratingCommandService;

    public RatingController(RatingCommandService ratingCommandService) {
        this.ratingCommandService = ratingCommandService;
    }

    /**
     * Endpoint para calificar un conductor.
     *
     * @param command El comando que contiene la información de la calificación.
     * @return ResponseEntity con la información de la calificación creada o un error.
     */
    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody CreateRatingCommand command) {
        Optional<Rating> rating = ratingCommandService.createRating(command);

        if (rating.isPresent()) {
            return new ResponseEntity<>(rating.get(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Si no se pudo crear la calificación
        }
    }
}
