package com.whotere.rationplanner.representation.controller;

import com.whotere.rationplanner.representation.dto.MeasurementUnitDto;
import com.whotere.rationplanner.domain.mapper.MeasurementUnitMapper;
import com.whotere.rationplanner.data.model.MeasurementUnit;
import com.whotere.rationplanner.domain.service.MeasurementUnitService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/measurementUnit")
@RequiredArgsConstructor
public class MeasurementUnitController {

    private final MeasurementUnitService measurementUnitService;
    private final MeasurementUnitMapper measurementUnitMapper;

    @ApiOperation("Получение списка всех доступных единиц измерения (гр, мл и т.п.)")
    @GetMapping
    public ResponseEntity<List<MeasurementUnitDto>> getUnits() {

        List<MeasurementUnit> measurementUnits = measurementUnitService.getUnits();

        return ResponseEntity.ok(
                measurementUnits.stream()
                        .map(measurementUnitMapper::toMeasurementUnitDto)
                        .collect(Collectors.toList())
        );
    }
}
