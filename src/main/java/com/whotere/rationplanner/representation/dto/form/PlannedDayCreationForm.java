package com.whotere.rationplanner.representation.dto.form;

import com.whotere.rationplanner.representation.dto.EntityIdReferenceDto;
import com.whotere.rationplanner.representation.dto.TimeDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlannedDayCreationForm {

    private String name;
    private List<PlannedDayMealInformation> plannedDayMeals;

    @Getter
    @Setter
    public static class PlannedDayMealInformation {

        private EntityIdReferenceDto meal;
        private TimeDto time;
    }
}
