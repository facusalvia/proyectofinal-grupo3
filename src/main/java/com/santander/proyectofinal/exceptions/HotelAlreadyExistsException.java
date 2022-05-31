package com.santander.proyectofinal.exceptions;

public class HotelAlreadyExistsException extends RuntimeException {
    public HotelAlreadyExistsException() {
        super("Hotel already exists");
    }
}
