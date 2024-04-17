package com.whotere.rationplanner.domain.service;

import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

public interface ValidationService {

    void validateThrowExceptionIfInvalid(Object target, Validator... validators);

    BindingResult validate(Object target, Validator... validators);
}
