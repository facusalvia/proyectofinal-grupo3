package com.santander.proyectofinal.exceptions;

public class RepositorySaveException extends RuntimeException{
    public RepositorySaveException() {
        super("Error when trying to add hotel or flight");
    }
}
