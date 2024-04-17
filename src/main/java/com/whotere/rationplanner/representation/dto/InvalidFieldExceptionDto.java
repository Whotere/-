package com.whotere.rationplanner.representation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidFieldExceptionDto extends ExceptionDto {

    private String field;
}
