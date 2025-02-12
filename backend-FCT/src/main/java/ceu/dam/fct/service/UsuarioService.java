package ceu.dam.fct.service;

import java.util.List;

import ceu.dam.fct.model.Alumno;
import ceu.dam.fct.model.RegistroPractica;

public interface UsuarioService {

	public Boolean login(String nombreUsuario, String password);

	public boolean cambiarContrasena(String nuevaContrasena, String usuarioLogado);

	public Alumno obtenerDatosAlumno(String usuarioLogado);

	public List<RegistroPractica> consultarRegistros(String usuarioLogado, String fechaDesde, String fechaHasta,
			String filtro);

	public boolean altaRegistroPractica(String usuarioLogado, int fechaId, double horas, String detalle);

	public boolean borrarRegistroPractica(int registroId, String usuarioLogado);

	public void cerrarSesion();

	public void salir();

}
