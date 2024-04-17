package com.whotere.rationplanner.representation.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class MealDto {

    private String id;
    private String name;
    private String description;
    private TimeDto cookingDuration;
    private String recipe;
    private Set<IngredientDto> ingredients;
    private Double price;
    private PhotoDto photo;
}
