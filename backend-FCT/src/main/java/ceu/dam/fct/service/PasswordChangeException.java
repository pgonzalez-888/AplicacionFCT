package ceu.dam.fct.service;

public class PasswordChangeException extends Exception {

	/**
	 * Excepción que controla errores a la hora de cambiar la contraseña de un usuario
	 */
	private static final long serialVersionUID = -6559166004827989354L;

	public PasswordChangeException() {
	}

	public PasswordChangeException(String message) {
		super(message);
	}

	public PasswordChangeException(Throwable cause) {
		super(cause);
	}

	public PasswordChangeException(String message, Throwable cause) {
		super(message, cause);
	}

	public PasswordChangeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
