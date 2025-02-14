package ceu.dam.fct.service;

import java.util.List;

import ceu.dam.fct.model.Alumno;
import ceu.dam.fct.model.RegistroPractica;
import ceu.dam.fct.model.Usuario;

public interface UsuarioService {

	public Usuario login(String nombreUsuario, String contrasena) throws UserNotFoundException, UserUnauthorizedException;

	public void cambiarContrasena(Long usuarioId, String antiguaContrasena, String nuevaContrasena)
			throws PasswordChangeException, UserUnauthorizedException, UserNotFoundException;

	public Alumno obtenerDatosAlumno(Long usuarioId) throws UserNotFoundException;

	public List<RegistroPractica> consultarRegistros(Long usuarioId, String fechaDesde, String fechaHasta, String filtro)
			throws UserNotFoundException;

	public void altaRegistroPractica(Long usuarioId, Long fechaId, Integer horas, String detalle)
			throws UserNotFoundException, FechaNoDisponibleException;

	public void borrarRegistroPractica(Long usuarioId, Long registroId) throws UserNotFoundException, RegistroNoEncontradoException;


}
