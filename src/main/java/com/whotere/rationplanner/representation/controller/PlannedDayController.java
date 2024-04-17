package com.whotere.rationplanner.representation.controller;

import com.whotere.rationplanner.representation.dto.PlannedDayDto;
import com.whotere.rationplanner.representation.dto.form.PlannedDayCreationForm;
import com.whotere.rationplanner.domain.mapper.PlannedDayMapper;
import com.whotere.rationplanner.data.model.PlannedDay;
import com.whotere.rationplanner.domain.service.PlannedDayService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/plannedDay")
@RequiredArgsConstructor
public class PlannedDayController {

    private final PlannedDayService plannedDayService;
    private final PlannedDayMapper plannedDayMapper;

    @ApiOperation(
            value = "Создание нового шаблона плана питания на день",
            notes = "Здесь создаётся не расписание питания, привязанное к конкретной календарной дате, а именно шаблон" +
                    " плана питания на день. Затем этот шаблон можно будет многоразово использовать, назначая его на" +
                    " конкретные даты в календаре."
    )
    @PostMapping
    public ResponseEntity<PlannedDayDto> createPlannedDay(@RequestBody PlannedDayCreationForm plannedDayCreationForm) {

        PlannedDay plannedDayData = plannedDayMapper.toPlannedDay(plannedDayCreationForm);
        PlannedDay createdPlannedDay = plannedDayService.createPlannedDay(plannedDayData);

        return ResponseEntity.ok(plannedDayMapper.toPlannedDayDto(createdPlannedDay));
    }

    @ApiOperation("Получение информации о шаблоне плана питания на день по его id")
    @GetMapping("/{id}")
    public ResponseEntity<PlannedDayDto> getPlannedDayById(@PathVariable String id) {

        PlannedDay plannedDay = plannedDayService.getPlannedDayById(id);

        return ResponseEntity.ok(plannedDayMapper.toPlannedDayDto(plannedDay));
    }

    @ApiOperation("Получение списка шаблонов планов питания на день текущего пользователя")
    @GetMapping
    public ResponseEntity<Set<PlannedDayDto>> getPlannedDays() {

        Set<PlannedDay> plannedDays = plannedDayService.getCurrentUserPlannedDays();

        return ResponseEntity.ok(
                plannedDays
                        .stream()
                        .map(plannedDayMapper::toPlannedDayDto)
                        .collect(Collectors.toSet())
        );
    }

    @ApiOperation("Удаление шаблона плана питания на день по его id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlannedDayById(@PathVariable String id) {

        this.plannedDayService.deletePlannedDayById(id);

        return ResponseEntity.noContent().build();
    }
}
