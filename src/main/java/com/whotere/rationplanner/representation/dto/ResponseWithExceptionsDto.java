package com.whotere.rationplanner.representation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseWithExceptionsDto {

    private Set<? extends ExceptionDto> exceptions;
}
