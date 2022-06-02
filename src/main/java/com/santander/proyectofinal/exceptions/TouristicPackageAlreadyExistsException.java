package com.santander.proyectofinal.exceptions;

public class TouristicPackageAlreadyExistsException extends RuntimeException {
    public TouristicPackageAlreadyExistsException() {
        super("Ya existe un paquete turistico con este número");
    }
}
