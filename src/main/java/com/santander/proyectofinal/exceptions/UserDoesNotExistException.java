package com.santander.proyectofinal.exceptions;

public class UserDoesNotExistException extends RuntimeException{
    public UserDoesNotExistException() {
        super("User is not exist");
    }
}
