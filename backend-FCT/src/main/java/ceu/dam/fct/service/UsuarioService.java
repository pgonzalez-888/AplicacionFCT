package ceu.dam.fct.service;

import java.util.List;

import ceu.dam.fct.model.Alumno;
import ceu.dam.fct.model.RegistroPractica;
import ceu.dam.fct.model.Usuario;

public interface UsuarioService {

	public Usuario login(String nombreUsuario, String password);

	public Boolean cambiarContrasena(String nuevaContrasena, String usuarioLogado);

	public Alumno obtenerDatosAlumno(String usuarioLogado);

	public List<RegistroPractica> consultarRegistros(String usuarioLogado, String fechaDesde, String fechaHasta,
			String filtro);

	public Boolean altaRegistroPractica(String usuarioLogado, Integer fechaId, Integer horas, String detalle);

	public Boolean borrarRegistroPractica(Integer registroId, String usuarioLogado);

	public void cerrarSesion();

	public void salir();

}
