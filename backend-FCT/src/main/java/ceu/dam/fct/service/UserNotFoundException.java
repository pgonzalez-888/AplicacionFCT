package ceu.dam.fct.service;

public class UserNotFoundException extends Exception {

	/**
	 * Excepción que controla si se ha introducido un usuario que no existe
	 */
	private static final long serialVersionUID = -7581089964737615716L;

	public UserNotFoundException() {
	}

	public UserNotFoundException(String message) {
		super(message);
	}

	public UserNotFoundException(Throwable cause) {
		super(cause);
	}

	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
