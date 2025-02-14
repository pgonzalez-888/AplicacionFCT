package ceu.dam.fct.api.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ceu.dam.fct.service.UserNotFoundException;

@ControllerAdvice
public class UserNotFoundExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handle(UserNotFoundException e) {
		return ResponseEntity.badRequest().body(e.getCause() + ": " + e.getMessage());
	}

}
