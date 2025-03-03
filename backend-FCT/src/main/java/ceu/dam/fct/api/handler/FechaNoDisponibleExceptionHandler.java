package ceu.dam.fct.api.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ceu.dam.fct.service.FechaNoDisponibleException;

@ControllerAdvice
public class FechaNoDisponibleExceptionHandler {
    @ExceptionHandler(FechaNoDisponibleException.class)
    public ResponseEntity<String> handleFechaNoDisponibleException(FechaNoDisponibleException e) {
        return ResponseEntity.badRequest().body(e.getCause() + ": " + e.getMessage());
    }
}
