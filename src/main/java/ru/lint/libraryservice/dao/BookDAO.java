package ru.lint.libraryservice.dao;

import org.springframework.stereotype.Component;
import ru.lint.libraryservice.models.Book;
import ru.lint.libraryservice.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public interface BookDAO {
    List<Book> findBooks();

    Optional<Book> findBook(int id);

    Optional<Person> findBookOwner(int bookId);

    List<Book> findOwnerBooks(int ownerId);

    void update(Book editedBook, int id);

    void delete(int id);

    void insert(Book book);

    void assignOwner(int bookId, int ownerId);

    void freeOwner(int id);
}
