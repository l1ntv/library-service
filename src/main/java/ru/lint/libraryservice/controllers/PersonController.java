package ru.lint.libraryservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.lint.libraryservice.dao.PersonDAO;

@Controller
public class PersonController {
    private final PersonDAO personDAO;
    @Autowired
    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
}
