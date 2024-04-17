package com.whotere.rationplanner.domain.mapper;

import com.whotere.rationplanner.data.model.MeasurementUnit;
import com.whotere.rationplanner.representation.dto.MeasurementUnitDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MeasurementUnitMapper {

    MeasurementUnitDto toMeasurementUnitDto(MeasurementUnit measurementUnit);
}
