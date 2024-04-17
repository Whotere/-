package com.whotere.rationplanner.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "planned_days")
@Getter
@Setter
public class PlannedDay extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "plannedDay", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PlannedDayMeal> plannedDayMeals;

    @OneToMany(mappedBy = "plannedDay", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Schedule> schedules;

    public Double getPrice() {

        if(plannedDayMeals == null) return 0.0;

        double totalPrice = 0.0;

        for(PlannedDayMeal plannedDayMeal : plannedDayMeals) {
            totalPrice += plannedDayMeal.getPrice();
        }

        return totalPrice;
    }

    public Integer getAmountOfMeals() {

        if(plannedDayMeals == null) return 0;
        return plannedDayMeals.size();
    }

    public String getUserId() {

        if(user == null) return null;
        return user.getId();
    }
}