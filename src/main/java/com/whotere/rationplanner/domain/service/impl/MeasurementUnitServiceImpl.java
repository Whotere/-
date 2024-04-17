package com.whotere.rationplanner.domain.service.impl;

import com.whotere.rationplanner.data.model.MeasurementUnit;
import com.whotere.rationplanner.data.repository.MeasurementUnitRepository;
import com.whotere.rationplanner.domain.service.MeasurementUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeasurementUnitServiceImpl implements MeasurementUnitService {

    private final MeasurementUnitRepository measurementUnitRepository;

    @Override
    public List<MeasurementUnit> getUnits() {
        return measurementUnitRepository.findAll();
    }
}
