package com.tinqinacademy.bff.api.models.exceptions.errorHandler;

import com.tinqinacademy.bff.api.models.base.OperationInput;
import com.tinqinacademy.bff.api.models.exceptions.baseError.Error;
import com.tinqinacademy.bff.api.models.exceptions.customException.InputDataException;
import com.tinqinacademy.bff.api.models.exceptions.customException.InvalidBedTypeException;
import com.tinqinacademy.bff.api.models.exceptions.customException.InvalidRoomByIdExceptions;
import com.tinqinacademy.bff.api.models.exceptions.customException.InvalidRoomByRoomNumberException;
import com.tinqinacademy.bff.api.models.exceptions.errorWrapper.ErrorWrapper;
import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class ErrorHandlerImpl implements ErrorHandler {
    @Override
    public ErrorWrapper handleError(Throwable t) {
        if (t instanceof InvalidRoomByIdExceptions) {
            ErrorWrapper errorWrapper = ErrorWrapper.builder().build();
            errorWrapper.addErrors(
                    Error.builder()
                            .status(HttpStatus.NOT_FOUND)
                            .statusCode(HttpStatus.NOT_FOUND.value())
                            .message(t.getMessage())
                            .build());
            return errorWrapper;
        }
        if (t instanceof IllegalArgumentException) {
            ErrorWrapper errorWrapper = ErrorWrapper.builder().build();
            errorWrapper.addErrors(
                    Error.builder()
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .message(t.getMessage())
                            .build());
            return errorWrapper;
        }
        if (t instanceof InputDataException) {
            ErrorWrapper errorWrapper = ErrorWrapper.builder().build();
            errorWrapper.addErrors(
                    Error.builder()
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .message(t.getMessage())
                            .build());
            return errorWrapper;
        }
        if (t instanceof InvalidBedTypeException) {
            ErrorWrapper errorWrapper = ErrorWrapper.builder().build();
            errorWrapper.addErrors(
                    Error.builder()
                            .status(HttpStatus.NOT_FOUND)
                            .statusCode(HttpStatus.NOT_FOUND.value())
                            .message(t.getMessage())
                            .build()
            );
            return errorWrapper;
        }
        if(t instanceof InvalidRoomByRoomNumberException){
            ErrorWrapper errorWrapper = ErrorWrapper.builder().build();
            errorWrapper.addErrors(
                    Error.builder()
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .message(t.getMessage())
                            .build());
            return errorWrapper;
        }

        return null;

    }
    public ErrorWrapper handleViolations(Set<ConstraintViolation<OperationInput>> violations, HttpStatusCode statusCode) {
        List<Error> responses = violations.stream()
                .map(v -> Error.builder()
                        .message(v.getMessage())
                        .status((HttpStatus) statusCode)
                        .statusCode(statusCode.value())
                        .build())
                .toList();

        ErrorWrapper build = ErrorWrapper.builder()
                .errors(responses)
                .build();
        return build;
    }
}