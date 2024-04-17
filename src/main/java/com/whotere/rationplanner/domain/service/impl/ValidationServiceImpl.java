package com.whotere.rationplanner.domain.service.impl;

import com.whotere.rationplanner.domain.exception.InvalidEntityException;
import com.whotere.rationplanner.domain.service.ValidationService;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Override
    public void validateThrowExceptionIfInvalid(Object target, Validator... validators) {

        BindingResult bindingResult = validate(target, validators);

        if(bindingResult.hasErrors()) {
            throw new InvalidEntityException(bindingResult);
        }
    }

    @Override
    public BindingResult validate(Object target, Validator... validators) {
        DataBinder dataBinder = new DataBinder(target);
        dataBinder.addValidators(validators);

        dataBinder.validate();

        return dataBinder.getBindingResult();
    }
}
