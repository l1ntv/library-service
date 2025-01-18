package ru.lint.libraryservice.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.lint.libraryservice.models.Person;
import ru.lint.libraryservice.services.PersonService;

import java.util.Optional;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public String showAllPeople(Model model) {
        model.addAttribute("people", personService.findAllPeople());
        return "people/all";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, Model model) {
        Optional<Person> person = personService.findPerson(id);
        if (person.isPresent()) {
            model.addAttribute("person", person.get());
            return "people/show-person";
        }
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id, Model model) {
        Optional<Person> person = personService.findPerson(id);
        if (person.isPresent()) {
            model.addAttribute("person", person.get());
            return "people/edit-person";
        }
        return "redirect:/people";
    }

    @PatchMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/edit-person";
        personService.editPerson(person);
        return String.format("redirect:/people/%d", id);
    }

    @DeleteMapping("/{id}/delete")
    public String deletePerson(@PathVariable("id") int id) {
        personService.deletePerson(id);
        return "redirect:/people";
    }

    @GetMapping("/create")
    public String createPerson(@ModelAttribute("person") Person person) {
        return "people/create-person";
    }

    @PostMapping("/create")
    public String createPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/create-person";
        personService.createPerson(person);
        return "redirect:/people";
    }
}