package com.whotere.rationplanner.representation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientDto {

    private String id;
    private String name;
    private ProductDto productDto;
    private ProductQuantityDto productQuantityDto;
    private Double price;
}
