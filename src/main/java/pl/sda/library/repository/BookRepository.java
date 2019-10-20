package pl.sda.library.repository;

import org.springframework.stereotype.Repository;
import pl.sda.library.model.Book;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BookRepository {

    private Set<Book> books = initialize();

    private Set<Book> initialize() {
        return new HashSet<>(Arrays
            .asList(new Book(1, "Testy", "Kaczanowski"), new Book(2, "Dzuma", "Camus"),
                new Book(3, "W pustyni i w puszczy", "Sienkiewicz"),
                new Book(4, "Pan Tadeusz", "Mickiewicz"), new Book(5, "Dziady", "Mickiewicz"),
                new Book(6, "Anioly i demony", "Brown"), new Book(7, "Gra o tron", "Martin"),
                new Book(8, "Harry Potter", "Rowling"), new Book(9, "Testy", "Kaczanowski"),
                new Book(10, "Testy", "Kaczanowski")));
    }

    public Optional<Book> borrowBook(int id, LocalDate borrowedTill) {
        Optional<Book> any = books.stream().filter(book -> id == book.getId())
            .filter(book -> book.getBorrowedTill() == null).findAny();
        any.ifPresent(book -> book.setBorrowedTill(borrowedTill));
        return any;
    }

    public void returnBook(int bookId) {
        //        for (pl.sda.library.model.Book book : books) {alternatywa
        //            if (book.getId() == bookId) {
        //                book.setBorrowedTill(null);
        //            }
        //        }
        //        throw new RuntimeException("pl.sda.library.model.Book not found");
        books.stream().filter(book -> book.getId() == bookId).findFirst()
            .orElseThrow(() -> new RuntimeException("pl.sda.library.model.Book not found"))
            .setBorrowedTill(null);
    }

    public Book addBook(Book bookToAdd) {
        bookToAdd.setId(generateNextId());
        books.add(bookToAdd);
        return bookToAdd;
    }

    public boolean removeBook(int id) {
        Optional<Book> bookToRemove = books.stream().filter(book -> id == book.getId()).findAny();
        if (bookToRemove.isPresent()) {
            return books.remove(bookToRemove.get());
        }
        return false;
    }

    public Set<Book> getBooks(String title) {
        if (title == null) {
            return books;
        }
        return books.stream().filter(book -> book.getTitle().equals(title))
            .collect(Collectors.toSet());
    }

    private int generateNextId() {
        //        int max = 0;
        //        for(pl.sda.library.model.Book book : books) {
        //            if (book.getId() > max) {
        //                max = book.getId();
        //            }
        //        }
        //        return max + 1;
        return books.stream().mapToInt(Book::getId).max().getAsInt() + 1;
    }
}














