package com.tinqinacademy.bff.api.models.exceptions.customException;

public class InvalidBedTypeException extends RuntimeException {
    public InvalidBedTypeException(String message) {
        super(message);
    }
}
