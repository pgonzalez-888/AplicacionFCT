package ceu.dam.fct.service;

public class RegistroNoEncontradoException extends Exception {

	/**
	 * Excepción que controla si un registro no ha sido encontrado
	 */
	private static final long serialVersionUID = -8004796378865286492L;

	public RegistroNoEncontradoException() {
	}

	public RegistroNoEncontradoException(String message) {
		super(message);
	}

	public RegistroNoEncontradoException(Throwable cause) {
		super(cause);
	}

	public RegistroNoEncontradoException(String message, Throwable cause) {
		super(message, cause);
	}

	public RegistroNoEncontradoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
