package com.whotere.rationplanner.domain.validator;

import com.whotere.rationplanner.data.model.PlannedDay;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PlannedDayValidator implements Validator {

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return PlannedDay.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {

        PlannedDay plannedDay = (PlannedDay) target;

        validateName(plannedDay, errors);
    }

    public void validateName(@NonNull PlannedDay plannedDay, @NonNull Errors errors) {

        String name = plannedDay.getName();

        if (name == null || name.equals("")) {
            errors.rejectValue(
                    "name",
                    "name.invalid",
                    "Название дня не должно быть пустым"
            );
            return;
        }

        if(2 > name.length() || name.length() > 50) {
            errors.rejectValue(
                    "name",
                    "name.invalid",
                    "Название дня должно иметь длину от 2 до 50 символов"
            );
        }
    }
}
