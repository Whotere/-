package com.whotere.rationplanner.representation.controller;

import com.whotere.rationplanner.representation.dto.ScheduleDto;
import com.whotere.rationplanner.representation.dto.ScheduledPlannedDayDto;
import com.whotere.rationplanner.representation.dto.form.ScheduleCreationForm;
import com.whotere.rationplanner.domain.mapper.ScheduleMapper;
import com.whotere.rationplanner.domain.mapper.ScheduledPlannedDayMapper;
import com.whotere.rationplanner.data.model.Schedule;
import com.whotere.rationplanner.domain.service.ScheduleService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ScheduleMapper scheduleMapper;
    private final ScheduledPlannedDayMapper scheduledPlannedDayMapper;

    @ApiOperation(
            value = "Добавление нового расписания",
            notes = "Расписание - это, по сути, план питания на день, который назначается на определённую календарную " +
                    "дату и который можно сделать повторяемым с определённой периодичностью (например, раз в 7 дней)."
    )
    @PostMapping
    public ResponseEntity<ScheduleDto> createSchedule(@RequestBody ScheduleCreationForm scheduleCreationForm) {

        Schedule createdSchedule = scheduleService.createSchedule(scheduleMapper.toSchedule(scheduleCreationForm));

        return ResponseEntity.ok(scheduleMapper.toScheduleDto(createdSchedule));
    }

    @ApiOperation(
            value = "Получение расписания питания текущего пользователя на месяц",
            notes = "Месяц, для которого будет возвращено расписание, будет определён по дате в параметре date." +
                    " К какому месяцу будет пренадлежать дата в date, на тот месяц и вернётся расписание."
    )
    @GetMapping
    public ResponseEntity<List<ScheduledPlannedDayDto>> getMonthSchedule(
            @RequestParam("date") @DateTimeFormat(pattern = "ddMMyyyy") LocalDate date
    ) {

        return ResponseEntity.ok(
                scheduleService.getMonthSchedule(date)
                        .stream()
                        .map(scheduledPlannedDayMapper::toScheduledPlannedDayDto)
                        .collect(Collectors.toList())
        );
    }

    @ApiOperation("Получение информации о расписании питания по его id")
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDto> getScheduleById(@PathVariable String id) {

        Schedule schedule = scheduleService.getScheduleById(id);

        return ResponseEntity.ok(scheduleMapper.toScheduleDto(schedule));
    }

    @ApiOperation("Удаление расписания питания по его id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScheduleById(@PathVariable String id) {

        this.scheduleService.deleteScheduleById(id);

        return ResponseEntity.noContent().build();
    }
}

