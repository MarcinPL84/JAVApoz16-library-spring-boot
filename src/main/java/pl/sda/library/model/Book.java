package pl.sda.library.model;

import java.time.LocalDate;
import java.util.Objects;

public class Book {

    private int id;
    private String title;
    private String author;
    private LocalDate borrowedTill;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getBorrowedTill() {
        return borrowedTill;
    }

    public void setBorrowedTill(LocalDate borrowedTill) {
        this.borrowedTill = borrowedTill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Book book = (Book) o;
        return id == book.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "pl.sda.library.model.Book{" + "id=" + id + ", title='" + title + '\'' + ", author='" + author + '\''
            + ", borrowedTill=" + borrowedTill + '}';
    }
}
