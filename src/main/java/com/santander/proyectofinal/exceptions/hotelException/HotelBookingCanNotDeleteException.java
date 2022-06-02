package com.santander.proyectofinal.exceptions.hotelException;

public class HotelBookingCanNotDeleteException extends RuntimeException {
    public HotelBookingCanNotDeleteException() {
        super("No se puede eliminar una reserva que tiene paquetes");
    }
}
