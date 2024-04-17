package com.whotere.rationplanner.representation.exceptionsHandler;

import com.whotere.rationplanner.representation.dto.ResponseWithExceptionsDto;
import com.whotere.rationplanner.representation.dto.ExceptionDto;
import com.whotere.rationplanner.domain.exception.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;

@ControllerAdvice
public class SecurityRelatedExceptionsHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ResponseWithExceptionsDto> handleAuthenticationException(AuthenticationException e) {

        ExceptionDto exceptionDto = new ExceptionDto("authenticationException", e.getMessage());

        ResponseWithExceptionsDto responseDto = new ResponseWithExceptionsDto();
        responseDto.setExceptions(Collections.singleton(exceptionDto));

        return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
    }
}
