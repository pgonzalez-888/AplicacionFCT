package ceu.dam.fct.api.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ceu.dam.fct.service.PasswordChangeException;

@ControllerAdvice
public class PasswordChangeExceptionHandler {
    @ExceptionHandler(PasswordChangeException.class)
    public ResponseEntity<String> handlePasswordChangeException(PasswordChangeException e) {
        return ResponseEntity.badRequest().body(e.getCause() + ": " + e.getMessage());
    }
}
