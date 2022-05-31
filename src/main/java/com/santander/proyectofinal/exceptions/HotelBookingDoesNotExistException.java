package com.santander.proyectofinal.exceptions;

public class HotelBookingDoesNotExistException extends RuntimeException{
    public HotelBookingDoesNotExistException() {
        super("HotelBooking does not exists");
    }
}
