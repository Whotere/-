package com.whotere.rationplanner.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Product> products;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Meal> meals;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<PlannedDay> plannedDays;
}
