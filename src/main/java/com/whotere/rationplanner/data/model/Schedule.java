package com.whotere.rationplanner.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "schedules")
@Getter
@Setter
public class Schedule extends BaseEntity {

    @Column(nullable = false)
    private LocalDate startDate;

    private Integer nextRepeatAfterDays;

    @ManyToOne
    @JoinColumn(name = "planned_day_id", nullable = false)
    private PlannedDay plannedDay;

    public String getPlannedDayId() {

        if(plannedDay == null) return null;
        return plannedDay.getId();
    }
}