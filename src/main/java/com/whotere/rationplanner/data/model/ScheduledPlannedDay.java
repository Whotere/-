package com.whotere.rationplanner.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ScheduledPlannedDay {

    private Schedule schedule;
    private PlannedDay plannedDay;
    private LocalDate date;
}