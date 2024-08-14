package com.tinqinacademy.bff.api.models.exceptions.customException;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException(String message) {
        super(message);
    }
}