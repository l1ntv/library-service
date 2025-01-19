package ru.lint.libraryservice.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.lint.libraryservice.models.Book;
import ru.lint.libraryservice.models.Person;
import ru.lint.libraryservice.services.BookService;
import ru.lint.libraryservice.services.PersonService;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final PersonService personService;

    @Autowired
    private BookController(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    @GetMapping()
    public String showAllBooks(Model model) {
        model.addAttribute("books", bookService.findBooks());
        return "books/all";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model) {
        Optional<Book> book = bookService.findBook(id);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            Optional<Person> bookOwner = bookService.findBookOwner(id);
            if (bookOwner.isPresent())
                model.addAttribute("owner", bookOwner.get());
            else {
                model.addAttribute("owner", null);
                model.addAttribute("people", personService.findPeople());
            }
            return "books/show-book";
        }
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model) {
        Optional<Book> book = bookService.findBook(id);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            return "books/edit-book";
        }
        return "redirect:/books";
    }

    @PatchMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/edit-book";
        bookService.editBook(book, id);
        return String.format("redirect:/books/%d", id);
    }

    @DeleteMapping("/{id}/delete")
    public String deleteBook(@PathVariable("id") int id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @GetMapping("/create")
    public String createBook(@ModelAttribute("book") Book book) {
        return "books/create-book";
    }

    @PostMapping("/create")
    public String createPerson(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/create-book";
        bookService.createBook(book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String assignOwner(@PathVariable("id") int bookId, @RequestParam("ownerId") int ownerId) {
        bookService.assignOwner(bookId, ownerId);
        return String.format("redirect:/books/%d", bookId);
    }

    @PatchMapping("/{id}/free")
    public String freeOwner(@PathVariable("id") int id) {
        bookService.freeOwner(id);
        return String.format("redirect:/books/%d", id);
    }

}
