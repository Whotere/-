package com.whotere.rationplanner.domain.mapper;

import com.whotere.rationplanner.representation.dto.DateDto;
import com.whotere.rationplanner.representation.dto.TimeDto;
import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class DateAndTimeMapperImpl implements DateAndTimeMapper {

    @Override
    public Duration toDuration(TimeDto timeDto) {

        if(timeDto == null) return null;

        int durationInMinutes = 0;

        if(timeDto.getHours() != null) {
            durationInMinutes += timeDto.getHours() * 60;
        }

        if(timeDto.getMinutes() != null) {
            durationInMinutes += timeDto.getMinutes();
        }

        return Duration.ofMinutes(durationInMinutes);
    }

    @Override
    public TimeDto toTimeDto(Duration duration) {

        if(duration == null) return null;

        TimeDto timeDto = new TimeDto();

        timeDto.setHours((int) duration.toHours());
        timeDto.setMinutes((int) duration.toMinutes() % 60);

        return timeDto;
    }

    @Override
    public LocalTime toLocalTime(TimeDto timeDto) {

        if(timeDto == null) return null;

        if(0 > timeDto.getHours() || timeDto.getHours() > 23
                || 0 > timeDto.getMinutes() || timeDto.getMinutes() > 59) {
            return null;
        }

        return LocalTime.of(timeDto.getHours(), timeDto.getMinutes());
    }

    @Override
    public TimeDto toTimeDto(LocalTime localTime) {

        if(localTime == null) return null;

        return new TimeDto(localTime.getHour(), localTime.getMinute());
    }

    @Override
    public LocalDate toLocalDate(DateDto dateDto) {

        if(dateDto == null) {
            return null;
        }

        try {
            return LocalDate.of(dateDto.getYear(), dateDto.getMonth(), dateDto.getDay());
        } catch (DateTimeException e) {
            return null;
        }
    }

    @Override
    public DateDto toDateDto(LocalDate localDate) {

        DateDto dateDto = new DateDto();

        dateDto.setYear(localDate.getYear());
        dateDto.setMonth(localDate.getMonthValue());
        dateDto.setDay(localDate.getDayOfMonth());

        return dateDto;
    }
}
