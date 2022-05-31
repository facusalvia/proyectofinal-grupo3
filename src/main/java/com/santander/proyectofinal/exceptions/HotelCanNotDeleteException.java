package com.santander.proyectofinal.exceptions;

public class HotelCanNotDeleteException extends RuntimeException{
    public HotelCanNotDeleteException() {
        super("Hotel can't unsubscribe because it has current reservations");
    }
}
