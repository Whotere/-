package com.whotere.rationplanner.representation.dto.form;

import com.whotere.rationplanner.representation.dto.DateDto;
import com.whotere.rationplanner.representation.dto.EntityIdReferenceDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleCreationForm {

    private EntityIdReferenceDto plannedDay;
    private DateDto startDate;
    private Integer nextRepeatAfterDays;
}
