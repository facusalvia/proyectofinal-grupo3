package com.santander.proyectofinal.exceptions.flightException;

public class FlightReservationDoesNotExistException extends RuntimeException{
    public FlightReservationDoesNotExistException() {
        super("No existe reserva de vuelo");
    }
}
