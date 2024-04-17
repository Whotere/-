package com.whotere.rationplanner.domain.mapper;

import com.whotere.rationplanner.data.model.SecurityTokensHolder;
import com.whotere.rationplanner.representation.dto.SecurityTokensHolderDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SecurityTokensHolderMapper {

    SecurityTokensHolderDto toSecurityTokensHolderDto(SecurityTokensHolder securityTokensHolder);
}
