package com.whotere.rationplanner.domain.service;

import com.whotere.rationplanner.data.model.Schedule;
import com.whotere.rationplanner.data.model.ScheduledPlannedDay;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {

    Schedule createSchedule(Schedule scheduleData);

    List<ScheduledPlannedDay> getMonthSchedule(LocalDate date);

    Schedule getScheduleById(String id);

    void deleteScheduleById(String id);
}
