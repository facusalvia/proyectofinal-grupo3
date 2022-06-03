package com.santander.proyectofinal.exceptions;

public class QueryDidNotReturnAnyResult extends RuntimeException {
    public QueryDidNotReturnAnyResult() {
        super("No se obtuvo ningun resultado con los parametros proporcionados");
    }
}
