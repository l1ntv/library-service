package ru.lint.libraryservice.dao;

import ru.lint.libraryservice.models.Person;

import java.util.List;
import java.util.Optional;

public interface PersonDAO {
    List<Person> findPeople();

    Optional<Person> findPerson(int id);

    void update(Person editedPerson);

    void delete(int id);

    void insert(Person person);
}