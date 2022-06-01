package com.santander.proyectofinal.exceptions.flightException;

public class FlightCanNotDeleteException extends RuntimeException{

    public FlightCanNotDeleteException(){
        super("Flight can't unsubscribe because it has current reservations");
    }
}
