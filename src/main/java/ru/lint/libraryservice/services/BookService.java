package ru.lint.libraryservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.lint.libraryservice.dao.BookDAO;
import ru.lint.libraryservice.models.Book;
import ru.lint.libraryservice.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookService {
    private final BookDAO bookDAO;

    @Autowired
    private BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public List<Book> findBooks() {
        return this.bookDAO.findBooks();
    }

    public Optional<Book> findBook(int id) {
        return this.bookDAO.findBook(id);
    }

    public Optional<Person> findBookOwner(int bookId) {
        return this.bookDAO.findBookOwner(bookId);
    }
    public List<Book> findOwnerBooks(int ownerId) {
        return bookDAO.findOwnerBooks(ownerId);
    }

    public void editBook(Book editedBook, int id) {
        bookDAO.update(editedBook, id);
    }

    public void deleteBook(int id) {
        bookDAO.delete(id);
    }

    public void createBook(Book book) {
        bookDAO.insert(book);
    }

    public void assignOwner(int bookId, int ownerId) {
        bookDAO.assignOwner(bookId, ownerId);
    }

    public void freeOwner(int id) {
        bookDAO.freeOwner(id);
    }
}
