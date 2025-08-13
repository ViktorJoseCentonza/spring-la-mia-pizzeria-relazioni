package org.lessons.java.spring_la_mia_pizzeria_crud.controller;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.model.SpecialOffer;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.SpecialOffersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/specialOffers")
public class SpecialOfferController {

    @Autowired
    private SpecialOffersRepository repository;

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("specialOffer") SpecialOffer specialOfferForm,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "specialOffers/create";
        }

        repository.save(specialOfferForm);

        return "redirect:/pizza/" + specialOfferForm.getPizza().getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("specialOffer", repository.findById(id).get());

        return "specialOffers/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("specialOffer") SpecialOffer specialOfferForm,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "specialOffers/edit";
        }
        repository.save(specialOfferForm);
        return "redirect:/pizza/" + specialOfferForm.getPizza().getId();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Integer pizzaId = repository.findById(id).get().getPizza().getId();
        repository.deleteById(id);
        return "redirect:/pizza/" + pizzaId;
    }
}
