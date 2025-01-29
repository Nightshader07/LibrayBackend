package main.libraybackend.interfaces;

import main.libraybackend.models.Borrow;

import java.util.List;
import java.util.Optional;

public interface IBorrowService {
    List<Borrow> getAllBorrows();
    Optional<Borrow> getBorrowById(Long id);
    List<Borrow> getBorrowsByUserId(Long userId);
    List<Borrow> getBorrowsByBookId(Long bookId);
    Borrow saveBorrow(Borrow borrow);
    Borrow updateBorrow(Long id, Borrow borrowDetails);
    void deleteBorrow(Long id);
}
