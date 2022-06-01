package com.santander.proyectofinal.exceptions;

public class UserDoesNotExistException extends RuntimeException{
    public UserDoesNotExistException() {
        super("Usuario inexistente");
    }
}
