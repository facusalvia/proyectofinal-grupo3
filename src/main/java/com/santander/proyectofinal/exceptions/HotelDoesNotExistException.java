package com.santander.proyectofinal.exceptions;

public class HotelDoesNotExistException extends RuntimeException {
    public HotelDoesNotExistException() {
        super("Hotel does not exists");
    }
}
