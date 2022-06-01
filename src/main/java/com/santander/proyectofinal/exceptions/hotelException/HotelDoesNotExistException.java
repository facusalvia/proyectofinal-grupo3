package com.santander.proyectofinal.exceptions.hotelException;

public class HotelDoesNotExistException extends RuntimeException {
    public HotelDoesNotExistException() {
        super("Hotel does not exists");
    }
}
