package com.whotere.rationplanner.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product extends BaseEntity {

    @Column(nullable = false)
    private String name;

    private String producer;

    @Column(nullable = false)
    private Double price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_quantity_id", referencedColumnName = "id", nullable = false)
    private ProductQuantity quantity;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    private Photo photo;

    @OneToMany(mappedBy = "meal", fetch = FetchType.LAZY)
    private Set<Ingredient> relatedIngredients;

    public Double quantityDifferenceCoef(ProductQuantity productQuantity) {
        return productQuantity.getAmount() / quantity.getAmount();
    }

    public Double getQuantityAmount() {

        if(quantity == null) return null;
        return quantity.getAmount();
    }

    public String getUserId() {

        if(user == null) return null;
        return user.getId();
    }

    public String getMeasurementUnitId() {

        if(quantity == null) return null;
        return quantity.getMeasurementUnitId();
    }
}
