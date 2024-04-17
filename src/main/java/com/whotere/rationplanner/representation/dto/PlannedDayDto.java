package com.whotere.rationplanner.representation.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlannedDayDto {

    private String id;
    private String name;
    private Double price;
    private Integer amountOfMeals;
    private List<PlannedDayMealDto> plannedDayMeals;
}
