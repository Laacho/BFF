package com.tinqinacademy.bff.api.models.exceptions.customException;

public class InvalidRoomByIdExceptions extends RuntimeException{
    public InvalidRoomByIdExceptions(String message) {
        super(message);
    }
}
