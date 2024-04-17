package com.whotere.rationplanner.representation.exceptionsHandler;

import com.whotere.rationplanner.representation.dto.ResponseWithExceptionsDto;
import com.whotere.rationplanner.representation.dto.ExceptionDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

@ControllerAdvice
public class RestExceptionsHandler extends ResponseEntityExceptionHandler {

    @Override
    @NonNull
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            @NonNull HttpMessageNotReadableException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatus status,
            @NonNull WebRequest request) {

        ExceptionDto exceptionDto = new ExceptionDto("invalidRequestBody", "Тело запроса невалидно.");

        ResponseWithExceptionsDto responseDto = new ResponseWithExceptionsDto(Collections.singleton(exceptionDto));
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);

    }
}
