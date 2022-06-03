package com.santander.proyectofinal.exceptions;

public class CountPackageDistintTwoException extends RuntimeException{
    public CountPackageDistintTwoException() {
        super("Error, count package more than two");
    }
}
