package ru.bluewhale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bluewhale.dao.PersonDAO;
import ru.bluewhale.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private PersonDAO personDAO;

    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(Model model) {
        System.out.println("index page: " + personDAO.index());
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("person", new Person());
        return "people/create";
    }

    @PostMapping()
    public String createPerson(
            @ModelAttribute("person")
            @Valid Person person,
            BindingResult bindingResult) {
        System.out.println("bindingResult " + bindingResult.hasErrors());
        if (bindingResult.hasErrors()) {
            return "people/create";
        }
        personDAO.save(person);

        return "redirect:/people";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(
            @ModelAttribute("person")
            @Valid Person person,
            @PathVariable("id") int id,
            BindingResult bindingResult) {
        System.out.println("update with patch");
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }

        personDAO.update(id, person);
        return "redirect:/people/{id}";
    }

    @ModelAttribute(name = "header")
    public String populateHeaderMessage() {
        return "Welcome to our website";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}
