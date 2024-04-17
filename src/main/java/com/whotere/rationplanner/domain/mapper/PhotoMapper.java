package com.whotere.rationplanner.domain.mapper;

import com.whotere.rationplanner.data.model.Photo;
import com.whotere.rationplanner.representation.dto.PhotoDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhotoMapper {

    PhotoDto toPhotoDto(Photo photo);
}
