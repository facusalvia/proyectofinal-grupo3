package com.santander.proyectofinal.exceptions;

public class BookingPeriodOutsideHotelDisponibilityPeriodException extends RuntimeException {
    public BookingPeriodOutsideHotelDisponibilityPeriodException() {
        super("Periodo de la reserva no esta dentro del periodo de disponibilidad del hotel");
    }
}
