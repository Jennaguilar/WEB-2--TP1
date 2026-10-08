package com.ejemplo.Tp1.exception;

public class ListaNoVaciaException extends RuntimeException {

    public ListaNoVaciaException(String mensaje) {
        super(mensaje);
    }
}