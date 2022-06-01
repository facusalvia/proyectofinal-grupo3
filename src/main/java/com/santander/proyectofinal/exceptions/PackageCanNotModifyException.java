package com.santander.proyectofinal.exceptions;

public class PackageCanNotModifyException extends RuntimeException{
    public PackageCanNotModifyException() {
        super("Error al modificar paquete numero inexistente");
    }
}
