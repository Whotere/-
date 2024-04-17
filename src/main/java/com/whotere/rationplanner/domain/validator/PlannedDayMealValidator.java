package com.whotere.rationplanner.domain.validator;

import com.whotere.rationplanner.data.model.Meal;
import com.whotere.rationplanner.data.model.PlannedDayMeal;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PlannedDayMealValidator implements Validator {

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return PlannedDayMeal.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {

        PlannedDayMeal plannedDayMeal = (PlannedDayMeal) target;

        validatePlannedDay(plannedDayMeal, errors);
        validateMeal(plannedDayMeal, errors);
        validateTime(plannedDayMeal, errors);
    }

    public void validatePlannedDay(@NonNull PlannedDayMeal plannedDayMeal, @NonNull Errors errors) {
        if(plannedDayMeal.getPlannedDay() == null) {
            errors.rejectValue(
                    "plannedDay",
                    "plannedDay.empty",
                    "День, к которому нужно привязать выбранное блюдо, указан неверно"
            );
        }
    }

    public void validateMeal(@NonNull PlannedDayMeal plannedDayMeal, @NonNull Errors errors) {

        Meal meal = plannedDayMeal.getMeal();

        if(meal == null || meal.getId() == null) {
            errors.rejectValue(
                    "meal",
                    "meal.empty",
                    "Блюдо, которое нужно добавить ко дню, не выбрано или выбрано неверно"
            );
        }
    }

    public void validateTime(@NonNull PlannedDayMeal plannedDayMeal, @NonNull Errors errors) {

        if(plannedDayMeal.getTime() == null) {
            errors.rejectValue(
                    "time",
                    "time.empty",
                    "Время начала приготовления блюда не выбрано или невалидно"
            );
        }
    }
}
