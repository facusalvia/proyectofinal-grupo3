package com.santander.proyectofinal.exceptions.hotelException;

public class HotelBookingDoesNotExistException extends RuntimeException{
    public HotelBookingDoesNotExistException() {
        super("No existe reserva de hotel");
    }
}
