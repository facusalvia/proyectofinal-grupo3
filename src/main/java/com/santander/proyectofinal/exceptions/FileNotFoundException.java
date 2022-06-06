package com.santander.proyectofinal.exceptions;

public class FileNotFoundException extends RuntimeException{
    public FileNotFoundException() {
        super("Error, could not create file");
    }
}
