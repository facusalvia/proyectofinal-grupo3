package com.santander.proyectofinal.exceptions.flightException;

public class FlightReservationCanNotDeleteException extends RuntimeException {
    public FlightReservationCanNotDeleteException() {
        super("No se puede eliminar una reserva que tiene paquetes");
    }
}
