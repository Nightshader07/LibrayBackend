package main.libraybackend.controllers;

import lombok.RequiredArgsConstructor;
import main.libraybackend.interfaces.IRatingService;
import main.libraybackend.models.Rating;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final IRatingService ratingService;

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings() {
        return ResponseEntity.ok(ratingService.getAllRatings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rating> getRatingById(@PathVariable Long id) {
        return ratingService.getRatingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Rating> addRating(
            @RequestParam Long bookId,
            @RequestParam Long userId,
            @RequestParam String ratingValue,
            @RequestParam String date) {

        return ResponseEntity.ok(ratingService.addRating(bookId, userId, ratingValue, date));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rating> updateRating(
            @PathVariable Long id,
            @RequestParam String newRating,
            @RequestParam String newDate) {

        return ResponseEntity.ok(ratingService.updateRating(id, newRating, newDate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long id) {
        ratingService.deleteRating(id);
        return ResponseEntity.noContent().build();
    }
}