package main.libraybackend.interfaces;


import main.libraybackend.models.Rating;

import java.util.List;
import java.util.Optional;

public interface IRatingService {

    List<Rating> getAllRatings();

    Rating addRating(Long bookId, Long userId, String ratingValue, String date);

    Optional<Rating> getRatingById(Long id);

    void deleteRating(Long id);

    Rating updateRating(Long id, String newRating, String newDate);
}
