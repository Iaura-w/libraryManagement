package org.project.librarymanagement.library;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library")
@AllArgsConstructor
public class LibraryController {
    private final LibraryService libraryService;

    @PostMapping("/borrow/{bookId}/customer/{customerId}")
    public ResponseEntity<BorrowStatus> borrowBook(@PathVariable String bookId, @PathVariable String customerId) {
        BorrowStatus borrowStatus = libraryService.borrowBook(bookId, customerId);
        return ResponseEntity.ok(borrowStatus);
    }

    @PostMapping("/return/{bookId}/customer/{customerId}")
    public ResponseEntity<ReturnStatus> returnBook(@PathVariable String bookId, @PathVariable String customerId) {
        ReturnStatus returnStatus = libraryService.returnBook(bookId, customerId);
        return ResponseEntity.ok(returnStatus);
    }

}
