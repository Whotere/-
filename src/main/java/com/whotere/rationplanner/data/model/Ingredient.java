package com.whotere.rationplanner.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ingredients")
@Getter
@Setter
public class Ingredient extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "meal_id", nullable = false)
    private Meal meal;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_quantity_id", nullable = false)
    private ProductQuantity productQuantity;

    public Double getPrice() {

        if(product == null || product.getPrice() == null) {
            return 0.0;
        }

        return product.getPrice() * product.quantityDifferenceCoef(productQuantity);
    }
}