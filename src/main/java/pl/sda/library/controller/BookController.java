package pl.sda.library.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.library.model.Book;
import pl.sda.library.service.OrderService;

import java.util.Optional;
import java.util.Set;

@RestController
public class BookController {

    private final OrderService orderService;

    public BookController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/books", produces = "application/json")
    public Set<Book> getAllBooks(@RequestParam(required = false) String title) {
        return orderService.getBooks(title);
    }

    @GetMapping(value = "/book/borrow/{id}", produces = "application/json")
    public ResponseEntity<Book> borrowBook(@PathVariable int id) {
        Optional<Book> book = orderService.borrowBook(id);
        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/book/add", consumes = "application/json")
    public ResponseEntity<Integer> addBook(@RequestBody Book book) {
        Book addedBook = orderService.addBook(book.getTitle(), book.getAuthor());
        return new ResponseEntity<>(addedBook.getId(), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/book/remove/{id}")
    public ResponseEntity<?> removeBook(@PathVariable int id) {
        boolean deleted = orderService.removeBook(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/book/retrieve/{id}")
    public ResponseEntity retrieveBook(@PathVariable int id) {
        orderService.returnBook(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}













