package com.santander.proyectofinal.exceptions;

public class PackageCanNotModifyException extends RuntimeException{
    public PackageCanNotModifyException() {
        super("Error when trying to modify package");
    }
}
