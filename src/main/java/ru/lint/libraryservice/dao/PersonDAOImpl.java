package ru.lint.libraryservice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.lint.libraryservice.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAOImpl implements PersonDAO {

    JdbcTemplate jdbcTemplate;

    @Autowired
    private PersonDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> findAllPeople() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Optional<Person> findPerson(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void editPerson(Person editedPerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, surname=?, patronymic=?, birth_year=? WHERE id=?", editedPerson.getName(), editedPerson.getSurname(), editedPerson.getPatronymic(), editedPerson.getBirthYear(), editedPerson.getId());
    }

    public void deletePerson(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

    public void createPerson(Person person) {
        jdbcTemplate.update("INSERT INTO Person (name, surname, patronymic, birth_year) VALUES (?, ?, ?, ?)", person.getName(), person.getSurname(), person.getPatronymic(), person.getBirthYear());
    }
}
