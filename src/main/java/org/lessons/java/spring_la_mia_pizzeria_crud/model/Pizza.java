package org.lessons.java.spring_la_mia_pizzeria_crud.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pizzas")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50, message = "you've exceeded the 50 character limit")
    @NotBlank(message = "must insert a name")
    private String name;

    @Size(max = 350, message = "you've exceeded the 350 character limit")
    @NotBlank(message = "must insert a description")
    @Lob
    private String description;

    @Size(max = 2048, message = "you've exceeded the 2048 character limit")
    @NotBlank(message = "must insert an image url")
    @Lob
    private String image_url;

    @NotNull(message = "must insert a price")
    @DecimalMin(value = "0.01", message = "must insert a price over 0.01")
    private Float price;

    @OneToMany(mappedBy = "pizza")
    private List<SpecialOffer> specialOffers;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return this.image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        if (price != null && price > 0.01) {
            this.price = price;
        }
    }

    public List<SpecialOffer> getSpecialOffers() {
        return this.specialOffers;
    }

    public void setSpecialOffers(List<SpecialOffer> specialOffers) {
        this.specialOffers = specialOffers;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
