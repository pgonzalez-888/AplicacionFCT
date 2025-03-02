package ceu.dam.fct.api.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ceu.dam.fct.service.RegistroNoEncontradoException;

@ControllerAdvice
public class RegistroNoEncontradoExceptionHandler {
    @ExceptionHandler(RegistroNoEncontradoException.class)
    public ResponseEntity<String> handleRegistroNoEncontradoException(RegistroNoEncontradoException e) {
        return ResponseEntity.badRequest().body(e.getCause() + ": " + e.getMessage());
    }
}
