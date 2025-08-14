package org.lessons.java.spring_la_mia_pizzeria_crud.controller;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.model.SpecialOffer;
import org.lessons.java.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.IngredientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/ingredients")
public class IngredientsController {
    @Autowired
    private IngredientsRepository ingredientsRepository;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("ingredients", ingredientsRepository.findAll());
        return "ingredients/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {

        model.addAttribute("ingredient", ingredientsRepository.findById(id).get());
        return "ingredients/show";
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("ingredient", new Ingredient());
        return "ingredients/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ingredient") Ingredient ingredientForm, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "ingredients/create";
        }
        ingredientsRepository.save(ingredientForm);
        return "redirect:/ingredients";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("ingredient", ingredientsRepository.findById(id).get());
        return "ingredients/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("ingredient") Ingredient ingredientForm, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "ingredients/edit";
        }
        ingredientsRepository.save(ingredientForm);
        return "redirect:/ingredients";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        Ingredient ingredientToDelete = ingredientsRepository.findById(id).get();

        for (Pizza pizza : ingredientToDelete.getPizzas()) {
            pizza.getIngredients().remove(ingredientToDelete);
        }
        ingredientsRepository.deleteById(id);
        return "redirect:/ingredients";
    }

}
