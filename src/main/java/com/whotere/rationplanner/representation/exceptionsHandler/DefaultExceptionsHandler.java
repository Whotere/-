package com.whotere.rationplanner.representation.exceptionsHandler;

import com.whotere.rationplanner.representation.dto.ExceptionDto;
import com.whotere.rationplanner.representation.dto.ResponseWithExceptionsDto;
import com.whotere.rationplanner.domain.exception.BadRequestException;
import com.whotere.rationplanner.domain.exception.FileNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.Collections;

@ControllerAdvice
public class DefaultExceptionsHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseWithExceptionsDto> handleBadRequestException(BadRequestException e) {

        ExceptionDto exceptionDto = new ExceptionDto("badRequest", e.getMessage());

        ResponseWithExceptionsDto responseDto = new ResponseWithExceptionsDto();
        responseDto.setExceptions(Collections.singleton(exceptionDto));

        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ResponseWithExceptionsDto> handleFileNotFoundException(FileNotFoundException e) {

        ExceptionDto exceptionDto = new ExceptionDto("fileNotFound", e.getMessage());

        ResponseWithExceptionsDto responseDto = new ResponseWithExceptionsDto();
        responseDto.setExceptions(Collections.singleton(exceptionDto));

        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ResponseWithExceptionsDto> handleFileSizeLimitExceeded(MaxUploadSizeExceededException e) {
        ExceptionDto exceptionDto = new ExceptionDto(
                "maxUploadFileSizeExceeded",
                "Максимальный размер загружаемого файла не должен превышать 8 Мб"
        );

        ResponseWithExceptionsDto responseDto = new ResponseWithExceptionsDto();
        responseDto.setExceptions(Collections.singleton(exceptionDto));

        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }
}
