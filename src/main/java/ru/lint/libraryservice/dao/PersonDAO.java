package ru.lint.libraryservice.dao;

import ru.lint.libraryservice.models.Person;

import java.util.List;
import java.util.Optional;

public interface PersonDAO {
    List<Person> findAllPeople();

    Optional<Person> findPerson(int id);

    void editPerson(Person editedPerson);

    void deletePerson(int id);

    void createPerson(Person person);
}