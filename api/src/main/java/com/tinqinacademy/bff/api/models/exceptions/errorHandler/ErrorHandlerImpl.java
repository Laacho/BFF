package com.tinqinacademy.bff.api.models.exceptions.errorHandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.bff.api.models.base.OperationInput;
import com.tinqinacademy.bff.api.models.exceptions.baseError.Error;
import com.tinqinacademy.bff.api.models.exceptions.customException.InputDataException;
import com.tinqinacademy.bff.api.models.exceptions.customException.InvalidBedTypeException;
import com.tinqinacademy.bff.api.models.exceptions.customException.InvalidRoomByIdExceptions;
import com.tinqinacademy.bff.api.models.exceptions.customException.InvalidRoomByRoomNumberException;
import com.tinqinacademy.bff.api.models.exceptions.errorWrapper.ErrorWrapper;
import feign.FeignException;
import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.ErrorResponse;

import java.util.List;
import java.util.Set;

@Component
public class ErrorHandlerImpl implements ErrorHandler {
    @Override
    public ErrorWrapper handleError(Throwable t) {
        if(t!=null){
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
        return ErrorWrapper.builder()
                .errors(responses)
                .build();
    }
    public ErrorWrapper handleFeignException(FeignException ex) {
        try {
            String errorBody = ex.contentUTF8();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode errorNode = objectMapper.readTree(errorBody);
            return objectMapper.treeToValue(errorNode, ErrorWrapper.class);
        } catch (Exception e) {
            return ErrorWrapper
                    .builder()
                    .errors(List.of(Error.builder()
                            .message("An error occurred while processing your request")
                                    .status(HttpStatus.valueOf(ex.status()))
                                    .statusCode(HttpStatus.valueOf(ex.status()).value())
                            .build()))
                    .build();
        }
    }
}
