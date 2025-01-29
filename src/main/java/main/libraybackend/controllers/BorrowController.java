package main.libraybackend.controllers;

import main.libraybackend.interfaces.IBorrowService;
import main.libraybackend.models.Borrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrows")
public class BorrowController {

    @Autowired
    private IBorrowService borrowService;

    // Get all borrows
    @GetMapping
    public ResponseEntity<List<Borrow>> getAllBorrows() {
        List<Borrow> borrows = borrowService.getAllBorrows();
        return ResponseEntity.ok(borrows);
    }

    // Get a borrow by ID
    @GetMapping("/{id}")
    public ResponseEntity<Borrow> getBorrowById(@PathVariable Long id) {
        return borrowService.getBorrowById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get borrows by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Borrow>> getBorrowsByUserId(@PathVariable Long userId) {
        List<Borrow> borrows = borrowService.getBorrowsByUserId(userId);
        return ResponseEntity.ok(borrows);
    }

    // Get borrows by book ID
    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Borrow>> getBorrowsByBookId(@PathVariable Long bookId) {
        List<Borrow> borrows = borrowService.getBorrowsByBookId(bookId);
        return ResponseEntity.ok(borrows);
    }

    // Add a new borrow
    @PostMapping
    public ResponseEntity<Borrow> createBorrow(@RequestBody Borrow borrow) {
        Borrow savedBorrow = borrowService.saveBorrow(borrow);
        return ResponseEntity.ok(savedBorrow);
    }

    // Update a borrow
    @PutMapping("/{id}")
    public ResponseEntity<Borrow> updateBorrow(@PathVariable Long id, @RequestBody Borrow borrowDetails) {
        try {
            Borrow updatedBorrow = borrowService.updateBorrow(id, borrowDetails);
            return ResponseEntity.ok(updatedBorrow);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a borrow
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrow(@PathVariable Long id) {
        borrowService.deleteBorrow(id);
        return ResponseEntity.noContent().build();
    }
}
