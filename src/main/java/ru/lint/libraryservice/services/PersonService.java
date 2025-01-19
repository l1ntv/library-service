package ru.lint.libraryservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.lint.libraryservice.dao.PersonDAO;
import ru.lint.libraryservice.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonService {
    private final PersonDAO personDAO;

    @Autowired
    private PersonService(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public List<Person> findPeople() {
        return personDAO.findPeople();
    }

    public Optional<Person> findPerson(int id) {
        return personDAO.findPerson(id);
    }

    public void editPerson(Person editedPerson) {
        personDAO.update(editedPerson);
    }

    public void deletePerson(int id) {
        personDAO.delete(id);
    }

    public void createPerson(Person person) {
        personDAO.insert(person);
    }
}