package com.santander.proyectofinal.exceptions.hotelException;

public class HotelsNoAvailableException extends RuntimeException{
    public HotelsNoAvailableException(){
        super("Hotels no available");
    }
}
