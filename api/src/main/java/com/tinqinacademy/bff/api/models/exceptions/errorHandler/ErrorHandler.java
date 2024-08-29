package com.tinqinacademy.bff.api.models.exceptions.errorHandler;

import com.tinqinacademy.bff.api.models.base.OperationInput;
import com.tinqinacademy.bff.api.models.exceptions.errorWrapper.ErrorWrapper;
import feign.FeignException;
import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpStatusCode;

import java.util.Set;

public interface ErrorHandler {

    ErrorWrapper handleError(Throwable t);

    ErrorWrapper handleViolations(Set<ConstraintViolation<OperationInput>> violations, HttpStatusCode statusCode);

     ErrorWrapper handleFeignException(FeignException ex);

}
