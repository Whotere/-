package com.whotere.rationplanner.domain.validator;

import com.whotere.rationplanner.data.model.PlannedDay;
import com.whotere.rationplanner.data.model.Schedule;
import com.whotere.rationplanner.data.repository.PlannedDayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class ScheduleValidator implements Validator {

    private final PlannedDayRepository plannedDayRepository;

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return Schedule.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {

        Schedule schedule = (Schedule) target;

        validatePlannedDay(schedule, errors);
        validateStartDate(schedule, errors);
        validateNextRepeatAfterDays(schedule, errors);
    }

    void validatePlannedDay(@NonNull Schedule schedule, @NonNull Errors errors) {

        PlannedDay plannedDay = schedule.getPlannedDay();

        if(plannedDay == null || plannedDay.getId() == null || !plannedDayRepository.existsById(plannedDay.getId())) {
            errors.rejectValue(
                    "plannedDay",
                    "plannedDay.empty",
                    "День, который нужно вставить в расписание, не выбран, либо выбран неверно"
            );
        }
    }

    void validateStartDate(@NonNull Schedule schedule, @NonNull Errors errors) {

        LocalDate startDate = schedule.getStartDate();

        if(startDate == null) {
            errors.rejectValue(
                    "startDate",
                    "startDate.empty",
                    "Дата, на которую нужно назначить день, не выбрана, либо выбрана неверно"
            );
            return;
        }

        // -1 день, дабы компенсировать возможное расхождение в датах из-за часового пояса
        if(startDate.isBefore(LocalDate.now().minusDays(1))) {
            errors.rejectValue(
                    "startDate",
                    "startDay.passed",
                    "Дата, на которую вы назначили день, уже прошла"

            );
        }
    }

    void validateNextRepeatAfterDays(@NonNull Schedule schedule, @NonNull Errors errors) {

        Integer nextRepeatAfterDays = schedule.getNextRepeatAfterDays();

        if(nextRepeatAfterDays == null) {
            return;
        }

        if(nextRepeatAfterDays < 1 || nextRepeatAfterDays > 9999) {
            errors.rejectValue(
                    "nextRepeatAfterDays",
                    "nextRepeatAfterDays.invalid",
                    String.format("Не получится повторять этот день каждые %d дн.", nextRepeatAfterDays)
            );
        }
    }
}
