package com.santander.proyectofinal.exceptions;

public class PackageDoesNotExistException extends RuntimeException{
    public PackageDoesNotExistException() {
        super("package does not exist");
    }

}
