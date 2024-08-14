package com.tinqinacademy.bff.rest;

import com.tinqinacademy.bff.api.models.base.OperationOutput;
import com.tinqinacademy.bff.api.models.exceptions.errorWrapper.ErrorWrapper;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {
    public ResponseEntity<?> handleResponse(Either<ErrorWrapper, ? extends OperationOutput> result) {
        if (result.isRight()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getLeft());

    }
}
