package com.whotere.rationplanner.domain.mapper;

import com.whotere.rationplanner.data.model.Meal;
import com.whotere.rationplanner.representation.dto.MealDto;
import com.whotere.rationplanner.representation.dto.TimeDto;
import com.whotere.rationplanner.representation.dto.form.MealCreationForm;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;

@Mapper(componentModel = "spring")
public abstract class MealMapper {

    @Autowired
    protected DateAndTimeMapper dateAndTimeMapper;

    public abstract Meal toMeal(MealCreationForm mealCreationForm);

    public abstract MealDto toMealDto(Meal meal);


    protected Duration map(TimeDto timeDto) {
        return dateAndTimeMapper.toDuration(timeDto);
    }

    protected TimeDto map(Duration duration) {
        return dateAndTimeMapper.toTimeDto(duration);
    }
}
