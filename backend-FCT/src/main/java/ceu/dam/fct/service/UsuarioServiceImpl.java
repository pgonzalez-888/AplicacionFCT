package ceu.dam.fct.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ceu.dam.fct.model.Alumno;
import ceu.dam.fct.model.Fecha;
import ceu.dam.fct.model.RegistroPractica;
import ceu.dam.fct.model.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private AlumnoRepository alumnoRepository;

	@Autowired
	private RegistroPracticaRepository registroPracticaRepository;

	@Autowired
	private FechaRepository fechaRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	private Usuario usuarioLogado;

	@Override
	public boolean login(String nombreUsuario, String contrasena) {
		// Validar que el usuario exista y esté asociado a un alumno
		Optional<Usuario> usuarioOpt = usuarioRepository.findByNombreUsuario(nombreUsuario);
		if (usuarioOpt.isPresent()) {
			Usuario usuario = usuarioOpt.get();
			if (passwordEncoder.matches(contrasena, usuario.getContrasena()) && usuario.getActivo()) {
				this.usuarioLogado = usuario; // Asignar el usuario logueado
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean cambiarContrasena(String nuevaContrasena, String usuarioLogado) {
		if (nuevaContrasena.length() < 8) {
			return false; // Verificar que la nueva contraseña tenga al menos 8 caracteres
		}
		usuarioLogado.setContrasena(passwordEncoder.encode(nuevaContrasena));
		usuarioRepository.save(usuarioLogado);
		return true;
	}

	@Override
	public Alumno obtenerDatosAlumno(String usuarioLogado) {
		Usuario usuario = usuarioRepository.findByNombreUsuario(usuarioLogado).get();
		return usuario.getUsuarioAsociado();
	}

	@Override
	public List<RegistroPractica> consultarRegistros(String usuarioLogado, String fechaDesde, String fechaHasta,
			String filtro) {
		Alumno alumno = obtenerDatosAlumno(usuarioLogado);
		return registroPracticaRepository.findRegistrosByAlumno(alumno, fechaDesde, fechaHasta, filtro);
	}

	@Override
	public boolean altaRegistroPractica(String usuarioLogado, int fechaId, double horas, String detalle) {
		Alumno alumno = obtenerDatosAlumno(usuarioLogado);
		Fecha fecha = fechaRepository.findById(fechaId).get();

		if (!esFechaDisponible(fecha, alumno)) {
			return false;
		}

		RegistroPractica registro = new RegistroPractica();
		registro.setAlumno(alumno);
		registro.setFecha(fecha);
		registro.setHoras(horas);
		registro.setDescripcion(detalle);
		registroPracticaRepository.save(registro);

		return true;
	}

	@Override
	public boolean borrarRegistroPractica(int registroId, String usuarioLogado) {
		RegistroPractica registro = registroPracticaRepository.findById(registroId).get();
		if (registro != null) {
			registroPracticaRepository.delete(registro);
			return true;
		}
		return false;
	}

	@Override
	public void cerrarSesion() {
		this.usuarioLogado = null;
	}

	@Override
	public void salir() {
		System.exit(0);
	}

	private boolean esFechaDisponible(Fecha fecha, Alumno alumno) {
		return !registroPracticaRepository.existsByAlumnoAndFecha(alumno, fecha);
	}

}
