package ZhandosSpring.controllers;

import ZhandosSpring.dao.BookDAO;
import ZhandosSpring.dao.PersonDAO;
import ZhandosSpring.models.Book;
import ZhandosSpring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }
    @PostMapping()
    public String save(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "books/new";
        }
        bookDAO.save(book);
        return "redirect:/books";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        Optional<Person> person1 = bookDAO.checkVladelec(id);
        if(person1.isPresent()) {
            model.addAttribute("owner", person1.get());
        }else {
            model.addAttribute("people", personDAO.index());
        }
        model.addAttribute("book", bookDAO.show(id));
        return "books/show";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }
    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookDAO.update(book, id);
        return "redirect:/books";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }
    @PatchMapping("/{id}/add")
    public String beru(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        bookDAO.knigaBeru(id, person);
        return "redirect:/books/" + id;
    }
    @PatchMapping("/{id}/remove")
    public String alu(@PathVariable("id") int id) {
        bookDAO.knigaAlu(id);
        return "redirect:/books/" + id;
    }
}
