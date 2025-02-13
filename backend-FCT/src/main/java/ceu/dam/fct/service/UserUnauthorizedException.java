package ceu.dam.fct.service;

public class UserUnauthorizedException extends Exception {

	/**
	 * Excepción que controla si se esta introduciendo una contraseña incorrecta o se esta intentando acceder a un usuario inactivo
	 */
	private static final long serialVersionUID = -3707029636970799878L;

	public UserUnauthorizedException() {
	}

	public UserUnauthorizedException(String message) {
		super(message);
	}

	public UserUnauthorizedException(Throwable cause) {
		super(cause);
	}

	public UserUnauthorizedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserUnauthorizedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
