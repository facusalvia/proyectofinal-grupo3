package com.santander.proyectofinal.exceptions.flightException;


public class CanceledFlightReservationDoesNotExistException extends RuntimeException{
    public CanceledFlightReservationDoesNotExistException(){
        super("There are no reservations canceled in the requested year");
    }
}
