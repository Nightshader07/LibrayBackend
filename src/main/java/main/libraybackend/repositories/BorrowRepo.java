package main.libraybackend.repositories;

import main.libraybackend.models.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepo extends JpaRepository<Borrow, Long> {
    List<Borrow> findByUser_Id(Long userId);

    List<Borrow> findByBook_Id(Long bookId);
}
