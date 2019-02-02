package com.spo.workplace.validator;

import com.spo.workplace.model.OptimizationRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

import java.util.Arrays;

import static org.junit.Assert.*;

public class RequestValidatorTest {

    private RequestValidator validator;

    @Before
    public void setUp() {
        validator = new RequestValidator();
    }

    @Test
    public void supports() {
        assertTrue(validator.supports(OptimizationRequest.class));
        assertFalse(validator.supports(Object.class));
    }

    @Test
    public void validate_checkNoErrors() {
        OptimizationRequest request = new OptimizationRequest(Arrays.asList(10,2,15), 5, 3);
        Errors errors = new BindException(request, "request");
        validator.validate(request, errors);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void validate_structureWithMoreThan100Rooms() {
        OptimizationRequest request = new OptimizationRequest(Arrays.asList(10,2,115), 5, 3);
        Errors errors = new BindException(request, "request");
        validator.validate(request, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getFieldErrorCount("rooms"));
        assertEquals("Structures can be up to 100 rooms", errors.getFieldError("rooms").getCode());
    }

    @Test
    public void validate_juniorWithMoreCapacityThanJunior() {
        OptimizationRequest request = new OptimizationRequest(Arrays.asList(10,2,2), 5, 7);
        Errors errors = new BindException(request, "request");
        validator.validate(request, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getFieldErrorCount("senior"));
        assertEquals("Senior must have more capacity than junior", errors.getFieldError("senior").getCode());
    }

    @Test
    public void validate_juniorWithEqualCapacityToJunior() {
        OptimizationRequest request = new OptimizationRequest(Arrays.asList(10,2,2), 5, 5);
        Errors errors = new BindException(request, "request");
        validator.validate(request, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getFieldErrorCount("senior"));
        assertEquals("Senior must have more capacity than junior", errors.getFieldError("senior").getCode());
    }
}