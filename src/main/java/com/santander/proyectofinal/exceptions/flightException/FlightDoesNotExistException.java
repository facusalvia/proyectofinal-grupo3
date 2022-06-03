package com.santander.proyectofinal.exceptions.flightException;

public class FlightDoesNotExistException extends RuntimeException{
    public FlightDoesNotExistException() {
        super("Flight does not exist");
    }
}
