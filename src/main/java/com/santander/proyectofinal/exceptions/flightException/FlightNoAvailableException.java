package com.santander.proyectofinal.exceptions.flightException;

public class FlightNoAvailableException extends RuntimeException{
    public FlightNoAvailableException() {
        super("Flights No Available");
    }
}
