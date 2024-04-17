package com.whotere.rationplanner.representation.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExceptionDto {

    private String code;
    private String message;
}