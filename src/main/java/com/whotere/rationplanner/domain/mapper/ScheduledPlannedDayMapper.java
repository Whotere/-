package com.whotere.rationplanner.domain.mapper;

import com.whotere.rationplanner.data.model.ScheduledPlannedDay;
import com.whotere.rationplanner.representation.dto.DateDto;
import com.whotere.rationplanner.representation.dto.ScheduledPlannedDayDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public abstract class ScheduledPlannedDayMapper {

    @Autowired
    protected DateAndTimeMapper dateAndTimeMapper;

    @Autowired
    protected PlannedDayMapper plannedDayMapper;

    @Mapping(
            target = "plannedDay",
            expression = "java(plannedDayMapper.toPlannedDayDto(scheduledPlannedDay.getPlannedDay()))"
    )
    public abstract ScheduledPlannedDayDto toScheduledPlannedDayDto(ScheduledPlannedDay scheduledPlannedDay);

    protected LocalDate map(DateDto dateDto) {
        return dateAndTimeMapper.toLocalDate(dateDto);
    }

    protected DateDto map(LocalDate localDate) {
        return dateAndTimeMapper.toDateDto(localDate);
    }
}
