package ru.lint.libraryservice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.lint.libraryservice.models.Book;
import ru.lint.libraryservice.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAOImpl implements BookDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BookDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> findBooks() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    @Override
    public Optional<Book> findBook(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
    }

    @Override
    public Optional<Person> findBookOwner(int bookId) {
        return jdbcTemplate.query("SELECT Person.id, Person.name, Person.surname, Person.patronymic, Person.birth_year FROM (Book INNER JOIN Person ON Book.user_id = Person.id) WHERE book_id = ?", new Object[]{bookId}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    @Override
    public List<Book> findOwnerBooks(int ownerId) {
        return jdbcTemplate.query("SELECT * From Book WHERE user_id = ?", new Object[]{ownerId}, new BeanPropertyRowMapper<>(Book.class));
    }

    @Override
    public void update(Book editedBook, int id) {
        jdbcTemplate.update("UPDATE Book SET name = ?, author_name = ?, creating_year = ? WHERE book_id = ?", editedBook.getName(), editedBook.getAuthorName(), editedBook.getCreatingYear(), id);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id = ?", id);
    }

    @Override
    public void insert(Book book) {
        jdbcTemplate.update("INSERT INTO Book (user_id, name, author_name, creating_year) VALUES (?, ?, ?, ?)", book.getUserId(), book.getName(), book.getAuthorName(), book.getCreatingYear());
    }

    @Override
    public void assignOwner(int bookId, int ownerId) {
        jdbcTemplate.update("UPDATE Book SET user_id = ? WHERE book_id = ?", ownerId, bookId);
    }

    @Override
    public void freeOwner(int id) {
        jdbcTemplate.update("UPDATE Book SET user_id = null WHERE book_id = ?", id);
    }
}