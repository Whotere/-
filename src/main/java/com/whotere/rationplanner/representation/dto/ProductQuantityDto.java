package com.whotere.rationplanner.representation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductQuantityDto {

    private String id;
    private Double amount;
    private MeasurementUnitDto measurementUnit;
}
