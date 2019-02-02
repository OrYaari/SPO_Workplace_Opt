package com.spo.workplace.validator;

import com.spo.workplace.model.OptimizationRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return OptimizationRequest.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        OptimizationRequest request = (OptimizationRequest) object;

        if (request.getRooms().stream().anyMatch(room -> room > 100)) {
            errors.rejectValue("rooms","Structures can be up to 100 rooms");
        }

        if (request.getSenior() <= request.getJunior()) {
            errors.rejectValue("senior","Senior must have more capacity than junior");
        }
    }
}
