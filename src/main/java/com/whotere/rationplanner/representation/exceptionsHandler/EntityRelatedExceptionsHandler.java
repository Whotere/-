package com.whotere.rationplanner.representation.exceptionsHandler;

import com.whotere.rationplanner.representation.dto.InvalidFieldExceptionDto;
import com.whotere.rationplanner.representation.dto.ResponseWithExceptionsDto;
import com.whotere.rationplanner.representation.dto.ExceptionDto;
import com.whotere.rationplanner.domain.exception.AccessDeniedException;
import com.whotere.rationplanner.domain.exception.EntityNotFoundException;
import com.whotere.rationplanner.domain.exception.InvalidEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ControllerAdvice
public class EntityRelatedExceptionsHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseWithExceptionsDto> handleEntityNotFoundException(EntityNotFoundException e) {

        ExceptionDto exceptionDto = new ExceptionDto("entityNotFound", e.getMessage());

        ResponseWithExceptionsDto responseDto = new ResponseWithExceptionsDto();
        responseDto.setExceptions(Collections.singleton(exceptionDto));

        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<ResponseWithExceptionsDto> handleInvalidEntityException(InvalidEntityException e) {

        Set<InvalidFieldExceptionDto> exceptionDtos = new HashSet<>();

        BindingResult entityBindingResult = e.getBindingResult();

        for(FieldError fieldError : entityBindingResult.getFieldErrors()) {

            InvalidFieldExceptionDto exceptionDto = new InvalidFieldExceptionDto();

            exceptionDto.setCode("invalidField");
            exceptionDto.setField(fieldError.getField());
            exceptionDto.setMessage(fieldError.getDefaultMessage());

            exceptionDtos.add(exceptionDto);
        }

        ResponseWithExceptionsDto responseDto = new ResponseWithExceptionsDto(exceptionDtos);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseWithExceptionsDto> handleAccessDeniedException(AccessDeniedException e) {

        ExceptionDto exceptionDto = new ExceptionDto("accessDenied", e.getMessage());

        Set<ExceptionDto> exceptionDtos = new HashSet<>();
        exceptionDtos.add(exceptionDto);

        ResponseWithExceptionsDto responseDto = new ResponseWithExceptionsDto();
        responseDto.setExceptions(exceptionDtos);

        return new ResponseEntity<>(responseDto, HttpStatus.FORBIDDEN);

    }
}
