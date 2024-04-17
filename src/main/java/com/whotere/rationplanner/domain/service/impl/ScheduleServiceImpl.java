package com.whotere.rationplanner.domain.service.impl;

import com.whotere.rationplanner.data.model.PlannedDay;
import com.whotere.rationplanner.data.model.Schedule;
import com.whotere.rationplanner.data.model.ScheduledPlannedDay;
import com.whotere.rationplanner.data.model.User;
import com.whotere.rationplanner.data.repository.PlannedDayRepository;
import com.whotere.rationplanner.data.repository.ScheduleRepository;
import com.whotere.rationplanner.domain.exception.AccessDeniedException;
import com.whotere.rationplanner.domain.exception.EntityNotFoundException;
import com.whotere.rationplanner.domain.service.PrincipalService;
import com.whotere.rationplanner.domain.service.ScheduleService;
import com.whotere.rationplanner.domain.service.ValidationService;
import com.whotere.rationplanner.domain.validator.ScheduleValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final PrincipalService principalService;
    private final ScheduleRepository scheduleRepository;
    private final PlannedDayRepository plannedDayRepository;

    private final ValidationService validationService;
    private final ScheduleValidator scheduleValidator;

    @Override
    public Schedule createSchedule(Schedule scheduleData) {

        User user = principalService.getPrincipal();

        validationService.validateThrowExceptionIfInvalid(scheduleData, scheduleValidator);

        PlannedDay loadedPlannedDayFromDb = plannedDayRepository.findById(scheduleData.getPlannedDayId()).get();

        if(!user.getId().equals(loadedPlannedDayFromDb.getUserId())) {
            throw new AccessDeniedException(
                    String.format("Вы не имеете доступа ко дню c id [%s]", scheduleData.getPlannedDayId())
            );
        }

        scheduleRepository.saveAndFlush(scheduleData);

        scheduleData.setPlannedDay(loadedPlannedDayFromDb);

        return scheduleData;
    }

    @Override
    @Transactional
    public List<ScheduledPlannedDay> getMonthSchedule(LocalDate date) {

        User user = principalService.getPrincipal();

        Set<Schedule> userSchedules = scheduleRepository.findAllByUser(user);

        List<ScheduledPlannedDay> scheduledPlannedDays = new ArrayList<>();

        for(Schedule schedule : userSchedules) {

            LocalDate currentDate = schedule.getStartDate();

            // пока очередная дата не вышла за пределы месяца, на который нужно сгенерировать расписание
            while(currentDate.getYear() < date.getYear()
                    || currentDate.getYear() == date.getYear() && currentDate.getMonthValue() <= date.getMonthValue()) {

                // если очередная дата входит в этот месяц, то сохраняем её в список
                if(currentDate.getYear() == date.getYear() && currentDate.getMonthValue() == date.getMonthValue()) {
                    scheduledPlannedDays.add(
                            new ScheduledPlannedDay(
                                schedule, schedule.getPlannedDay(), currentDate
                            )
                    );
                }

                int lengthOfCurrentMonth = YearMonth.of(date.getYear(), date.getMonthValue()).lengthOfMonth();
                currentDate = currentDate.plusDays(
                        Optional.ofNullable(schedule.getNextRepeatAfterDays()).orElse(lengthOfCurrentMonth)
                );
            }

        }

        return scheduledPlannedDays;
    }

    @Override
    public Schedule getScheduleById(String id) {

        User user = principalService.getPrincipal();

        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Расписание с id [%s] не найдено", id))
        );

        if(!schedule.getPlannedDay().getUserId().equals(user.getId())) {
            throw new AccessDeniedException(String.format("Вы не имеете доступа к расписанию с id [%s]", id));
        }

        return schedule;
    }

    @Override
    public void deleteScheduleById(String id) {

        User user = principalService.getPrincipal();

        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Расписание с id [%s] не найдено", id))
        );

        if(!schedule.getPlannedDay().getUserId().equals(user.getId())) {
            throw new AccessDeniedException(String.format("Вы не имеете доступа к расписанию с id [%s]", id));
        }

        scheduleRepository.deleteById(id);
    }
}