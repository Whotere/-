package com.whotere.rationplanner.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Duration;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "meals")
@Getter
@Setter
public class Meal extends BaseEntity {

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "cooking_duration", nullable = false)
    private Duration cookingDuration;

    @Column(length = 10000)
    private String recipe;

    @OneToMany(mappedBy = "meal", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Ingredient> ingredients;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "photo_id", referencedColumnName = "id", unique = true)
    private Photo photo;

    @OneToMany(mappedBy = "meal", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PlannedDayMeal> plannedDayMeals;

    public Double getPrice() {

        if(this.ingredients == null) {
            return 0.0;
        }

        Double totalPrice = 0.0;

        for(Ingredient ingredient : ingredients) {
            totalPrice += ingredient.getPrice();
        }

        return totalPrice;
    }

    public String getUserId() {
        if(user == null) {
            return null;
        }

        return user.getId();
    }
}