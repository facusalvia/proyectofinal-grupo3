package com.santander.proyectofinal.exceptions.hotelException;

public class HotelAlreadyExistsException extends RuntimeException {
    public HotelAlreadyExistsException() {
        super("Hotel already exists");
    }
}
