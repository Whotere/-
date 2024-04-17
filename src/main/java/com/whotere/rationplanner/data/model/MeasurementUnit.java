package com.whotere.rationplanner.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "measurement_units")
@Getter
@Setter
public class MeasurementUnit extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "measurementUnit", fetch = FetchType.LAZY)
    private Set<ProductQuantity> relatedQuantities;
}