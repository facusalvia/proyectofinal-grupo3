package com.santander.proyectofinal.exceptions.flightException;

public class FlightAlreadyExistsException extends RuntimeException{

        public FlightAlreadyExistsException() {
            super("Flight already exist");
        }

}
