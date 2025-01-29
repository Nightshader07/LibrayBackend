package main.libraybackend.services;

import lombok.RequiredArgsConstructor;
import main.libraybackend.interfaces.IRatingService;
import main.libraybackend.models.Book;
import main.libraybackend.models.Rating;
import main.libraybackend.models.User;
import main.libraybackend.repositories.BookRepo;
import main.libraybackend.repositories.RatingRepo;
import main.libraybackend.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RatingService implements IRatingService {
    @Autowired
    private final RatingRepo ratingRepository;
    @Autowired
    private final BookRepo bookRepository;
    @Autowired
    private final UserRepo userRepository;


    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating addRating(Long bookId, Long userId, String ratingValue, String date) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with ID: " + bookId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        Rating rating = new Rating();
        rating.setBookk(book);
        rating.setUserr(user);
        rating.setRating(ratingValue);
        rating.setDate(date);

        return ratingRepository.save(rating);
    }

    @Override
    public Optional<Rating> getRatingById(Long id) {
        return ratingRepository.findById(id);
    }

    @Override
    public void deleteRating(Long id) {
        ratingRepository.deleteById(id);
    }

    @Override
    public Rating updateRating(Long id, String newRating, String newDate) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rating not found with ID: " + id));

        rating.setRating(newRating);
        rating.setDate(newDate);

        return ratingRepository.save(rating);
    }
}
