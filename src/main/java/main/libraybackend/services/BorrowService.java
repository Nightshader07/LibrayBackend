package main.libraybackend.services;

import main.libraybackend.interfaces.IBorrowService;
import main.libraybackend.models.Borrow;
import main.libraybackend.repositories.BorrowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BorrowService implements IBorrowService {
    @Autowired
    private BorrowRepo borrowRepository;
    @Override
    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    @Override
    public Optional<Borrow> getBorrowById(Long id) {
        return borrowRepository.findById(id);
    }

    @Override
    public List<Borrow> getBorrowsByUserId(Long userId) {
        return borrowRepository.findByUser_Id(userId);
    }

    @Override
    public List<Borrow> getBorrowsByBookId(Long bookId) {
        return borrowRepository.findByBook_Id(bookId);
    }

    @Override
    public Borrow saveBorrow(Borrow borrow) {
        return borrowRepository.save(borrow);
    }

    @Override
    public Borrow updateBorrow(Long id, Borrow borrowDetails) {
        return borrowRepository.findById(id)
                .map(borrow -> {
                    borrow.setBook(borrowDetails.getBook());
                    borrow.setUser(borrowDetails.getUser());
                    borrow.setDate(borrowDetails.getDate());
                    borrow.setReturnDate(borrowDetails.getReturnDate());
                    borrow.setStatus(borrowDetails.getStatus());
                    return borrowRepository.save(borrow);
                })
                .orElseThrow(() -> new RuntimeException("Borrow not found with id " + id));
    }

    @Override
    public void deleteBorrow(Long id) {
        borrowRepository.deleteById(id);
    }

}
