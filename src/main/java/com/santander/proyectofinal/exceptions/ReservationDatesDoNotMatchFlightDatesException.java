package com.santander.proyectofinal.exceptions;

public class ReservationDatesDoNotMatchFlightDatesException extends RuntimeException {
    public ReservationDatesDoNotMatchFlightDatesException() {
        super("Fechas de la reserva no coinciden con fechas del vuelo");
    }
}
