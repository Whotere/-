package com.whotere.rationplanner.data.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "quantities")
@NoArgsConstructor
@Getter
@Setter
public class ProductQuantity extends BaseEntity {

    @Column(nullable = false)
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "measurement_unit_id", nullable = false)
    private MeasurementUnit measurementUnit;

    @OneToOne(mappedBy = "quantity")
    private Product product;

    @OneToOne(mappedBy = "productQuantity")
    private Ingredient ingredient;

    public ProductQuantity(Double amount, MeasurementUnit measurementUnit) {
        this.amount = amount;
        this.measurementUnit = measurementUnit;
    }

    public String getMeasurementUnitId() {

        if(measurementUnit == null) return null;
        return measurementUnit.getId();
    }
}
