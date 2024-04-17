package com.whotere.rationplanner.domain.service;

import com.whotere.rationplanner.data.model.PlannedDay;

import java.util.Set;

public interface PlannedDayService {

    PlannedDay createPlannedDay(PlannedDay plannedDayData);

    PlannedDay getPlannedDayById(String id);

    Set<PlannedDay> getCurrentUserPlannedDays();

    void deletePlannedDayById(String id);
}
