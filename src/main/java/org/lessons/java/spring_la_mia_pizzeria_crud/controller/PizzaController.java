package org.lessons.java.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.model.SpecialOffer;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.SpecialOffersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/")
public class PizzaController {

    @Autowired
    private PizzaRepository repository;

    @Autowired
    private SpecialOffersRepository specialOffersRepository;

    @GetMapping("/pizzas")
    public String index(Model model) {
        List<Pizza> pizzas = repository.findAll();
        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }

    @GetMapping("/pizza/{id}")
    public String show(@PathVariable("id") int id, Model model) {

        model.addAttribute("pizza", repository.findById(id).get());
        return "pizzas/show";
    }

    @GetMapping("/search")
    public String show(@RequestParam(name = "query") String query, Model model) {

        List<Pizza> pizzas = repository.findByNameContainingIgnoreCase(query);

        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("pizza", new Pizza());
        return "pizzas/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza pizzaForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "pizzas/create";
        }
        repository.save(pizzaForm);
        return "redirect:/pizzas";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("pizza", repository.findById(id).get());
        return "pizzas/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza pizzaForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "pizzas/edit";
        }
        repository.save(pizzaForm);
        return "redirect:/pizzas";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Pizza pizza = repository.findById(id).get();

        for (SpecialOffer specialOfferToDelete : pizza.getSpecialOffers()) {
            specialOffersRepository.delete(specialOfferToDelete);
        }
        repository.delete(pizza);
        return "redirect:/pizzas";
    }

    @GetMapping("/pizza/{id}/new-specialOffer")
    public String createSpecialOffer(@PathVariable Integer id, Model model) {
        SpecialOffer specialOffer = new SpecialOffer();
        specialOffer.setPizza(repository.findById(id).get());

        model.addAttribute("specialOffer", specialOffer);
        return "specialOffers/create";
    }

}
