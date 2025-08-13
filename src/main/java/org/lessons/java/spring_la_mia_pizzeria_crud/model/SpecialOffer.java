package org.lessons.java.spring_la_mia_pizzeria_crud.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "offers")
public class SpecialOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50, message = "you've exceeded the 50 character limit")
    @NotBlank(message = "must insert a title")
    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "must insert a date")
    @Future(message = "the begin date cannot be set in the past")
    private LocalDate beginDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "must insert a date")
    @Future(message = "the end date cannot be set in the past or present")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getBeginDate() {
        return this.beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Pizza getPizza() {
        return this.pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

}
