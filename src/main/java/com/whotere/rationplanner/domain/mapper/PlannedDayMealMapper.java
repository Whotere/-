package com.whotere.rationplanner.domain.mapper;

import com.whotere.rationplanner.data.model.PlannedDayMeal;
import com.whotere.rationplanner.representation.dto.PlannedDayMealDto;
import com.whotere.rationplanner.representation.dto.TimeDto;
import com.whotere.rationplanner.representation.dto.form.PlannedDayCreationForm;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PlannedDayMealMapper {

    @Autowired
    protected DateAndTimeMapper dateAndTimeMapper;

    public abstract List<PlannedDayMeal> toPlannedDayMeals(
            List<PlannedDayCreationForm.PlannedDayMealInformation> plannedDayMealInformation
    );

    public abstract List<PlannedDayMealDto> toPlannedDayMealDtos(List<PlannedDayMeal> plannedDayMeals);

    protected LocalTime map(TimeDto timeDto) {
        return dateAndTimeMapper.toLocalTime(timeDto);
    }

    protected TimeDto map(LocalTime localTime) {
        return dateAndTimeMapper.toTimeDto(localTime);
    }
}
