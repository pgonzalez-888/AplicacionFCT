package ceu.dam.fct.service;

public class FechaNoDisponibleException extends Exception {

	/**
	 * Excepción que controla si una fecha no esta disponible para un alumno
	 */
	private static final long serialVersionUID = -686025820583330692L;

	public FechaNoDisponibleException() {
	}

	public FechaNoDisponibleException(String message) {
		super(message);
	}

	public FechaNoDisponibleException(Throwable cause) {
		super(cause);
	}

	public FechaNoDisponibleException(String message, Throwable cause) {
		super(message, cause);
	}

	public FechaNoDisponibleException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
