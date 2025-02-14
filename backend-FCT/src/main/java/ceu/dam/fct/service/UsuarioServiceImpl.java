package ceu.dam.fct.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ceu.dam.fct.model.Alumno;
import ceu.dam.fct.model.Fecha;
import ceu.dam.fct.model.RegistroPractica;
import ceu.dam.fct.model.Usuario;
import ceu.dam.fct.repository.AlumnoRepository;
import ceu.dam.fct.repository.FechaRepository;
import ceu.dam.fct.repository.RegistroPracticaRepository;
import ceu.dam.fct.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private AlumnoRepository alumnoRepository;

	@Autowired
	private RegistroPracticaRepository registroPracticaRepository;

	@Autowired
	private FechaRepository fechaRepository;

	@Override
	public Usuario login(String nombreUsuario, String contrasena) throws UserNotFoundException, UserUnauthorizedException {
	    log.debug("Realizando login con usuario " + nombreUsuario);
	    Optional<Usuario> usuarioOpt = usuarioRepository.findByNombreUsuario(nombreUsuario);
	    
	    if (usuarioOpt.isPresent()) {
	        Usuario usuario = usuarioOpt.get();
	        if (contrasena.equals(usuario.getContrasena()) && usuario.getActivo()) {
	            log.debug("Login exitoso para el usuario: " + nombreUsuario);
	            return usuario; // El frontend recibirá el usuario con su ID
	        } else {
	        	log.debug("Contraseña incorrecta o usuario inactivo");
	            throw new UserUnauthorizedException("Contraseña incorrecta o usuario inactivo");
	        }
	    } else {
	    	log.debug("No existe usuario con ese nombre");
	        throw new UserNotFoundException("No existe usuario con ese nombre");
	    }
	}


	@Override
	public void cambiarContrasena(Long usuarioId, String antiguaContrasena, String nuevaContrasena) 
	        throws PasswordChangeException, UserUnauthorizedException, UserNotFoundException {
	    log.debug("Intentando cambiar la contraseña para el usuario con ID: " + usuarioId);

	    Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);

	    if (!usuarioOpt.isPresent()) {
	        log.error("Error: Usuario con ID " + usuarioId + " no encontrado.");
	        throw new UserNotFoundException("Usuario no encontrado");
	    }

	    Usuario usuario = usuarioOpt.get();

	    if (!antiguaContrasena.equals(usuario.getContrasena())) {
	        log.error("Error: La contraseña actual es incorrecta para el usuario con ID " + usuarioId);
	        throw new UserUnauthorizedException("La contraseña actual es incorrecta");
	    }

	    if (nuevaContrasena.equals(antiguaContrasena)) {
	        log.error("Error: La nueva contraseña no puede ser igual a la antigua para el usuario con ID " + usuarioId);
	        throw new PasswordChangeException("La nueva contraseña no puede ser igual a la antigua");
	    }

	    if (nuevaContrasena.length() < 8) {
	        log.error("Error: La nueva contraseña no cumple con la longitud mínima para el usuario con ID " + usuarioId);
	        throw new PasswordChangeException("La contraseña debe tener al menos 8 caracteres");
	    }

	    usuario.setContrasena(nuevaContrasena);
	    usuarioRepository.save(usuario);

	    log.info("Contraseña cambiada con éxito para el usuario con ID " + usuarioId);
	}




	@Override
	public Alumno obtenerDatosAlumno(Long usuarioId) throws UserNotFoundException {
	    log.debug("Obteniendo datos del alumno asociado al usuario con ID: " + usuarioId);

	    Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
	    if (!usuarioOpt.isPresent()) {
	        log.error("Error: Usuario con ID " + usuarioId + " no encontrado.");
	        throw new UserNotFoundException("Usuario no encontrado");
	    }

	    Usuario usuario = usuarioOpt.get();
	    Alumno alumno = usuario.getUsuarioAsociado();

	    if (alumno == null) {
	        log.error("Error: No se encontró un alumno asociado al usuario con ID " + usuarioId);
	        throw new UserNotFoundException("No se encontró un alumno asociado a este usuario");
	    }

	    log.info("Datos del alumno obtenidos con éxito para el usuario con ID " + usuarioId);
	    return alumno;
	}




	@Override
	public List<RegistroPractica> consultarRegistros(Long usuarioId, String fechaDesde, String fechaHasta, String filtro) throws UserNotFoundException {
	    log.debug("Consultando registros para el usuario con ID: " + usuarioId);

	    Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
	    if (!usuarioOpt.isPresent()) {
	        log.error("Error: Usuario con ID " + usuarioId + " no encontrado.");
	        throw new UserNotFoundException("Usuario no encontrado");
	    }

	    Usuario usuario = usuarioOpt.get();
	    Alumno alumno = usuario.getUsuarioAsociado();

	    if (alumno == null) {
	        log.error("Error: No se encontró un alumno asociado al usuario con ID " + usuarioId);
	        throw new UserNotFoundException("No se encontró un alumno asociado a este usuario");
	    }

	    List<RegistroPractica> registros = registroPracticaRepository.findRegistrosByAlumno(alumno, fechaDesde, fechaHasta, filtro);

	    log.info("Registros obtenidos con éxito para el usuario con ID " + usuarioId);
	    return registros;
	}




	@Override
	public void altaRegistroPractica(Long usuarioId, Long fechaId, Integer horas, String detalle) 
	        throws FechaNoDisponibleException, UserNotFoundException {
	    log.debug("Creando un registro para el usuario con ID " + usuarioId + " y fecha ID " + fechaId);

	    Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
	    if (usuarioOpt.isPresent()) {
	        Usuario usuario = usuarioOpt.get();
	        
	        Alumno alumno = usuario.getUsuarioAsociado();
	        if (alumno == null) {
	            log.error("Error: No se encontró un alumno asociado al usuario con ID " + usuarioId);
	            throw new UserNotFoundException("No se encontró un alumno asociado a este usuario");
	        }

	        Optional<Fecha> fechaOpt = fechaRepository.findById(fechaId);
	        if (fechaOpt.isPresent()) {
	            Fecha fecha = fechaOpt.get();
	            if (!esFechaDisponible(fecha, alumno)) {
	                log.error("Error: La fecha con ID " + fechaId + " no está disponible para el usuario con ID " + usuarioId);
	                throw new FechaNoDisponibleException("La fecha no está disponible para este alumno");
	            }

	            // Crear y guardar el registro
	            RegistroPractica registro = new RegistroPractica();
	            registro.setAlumno(alumno);
	            registro.setFecha(fecha);
	            registro.setHoras(horas);
	            registro.setDescripcion(detalle);
	            registroPracticaRepository.save(registro);

	            log.info("Registro creado con éxito para el usuario con ID " + usuarioId);
	        } else {
	            log.error("Error: Fecha con ID " + fechaId + " no encontrada.");
	            throw new FechaNoDisponibleException("Fecha no disponible");
	        }
	    } else {
	        log.error("Error: Usuario con ID " + usuarioId + " no encontrado.");
	        throw new UserNotFoundException("Usuario no encontrado");
	    }
	}




	@Override
	public void borrarRegistroPractica(Long usuarioId, Long registroId) throws UserNotFoundException, RegistroNoEncontradoException {
	    log.debug("Intentando borrar el registro con ID " + registroId + " para el usuario con ID " + usuarioId);

	    Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
	    if (usuarioOpt.isPresent()) {
	        Usuario usuario = usuarioOpt.get();

	        Optional<RegistroPractica> registroOpt = registroPracticaRepository.findById(registroId);
	        if (registroOpt.isPresent()) {
	            RegistroPractica registro = registroOpt.get();
	            registroPracticaRepository.delete(registro);

	            log.info("Registro con ID " + registroId + " borrado con éxito para el usuario con ID " + usuarioId);
	        } else {
	            log.error("Error: Registro con ID " + registroId + " no encontrado.");
	            throw new RegistroNoEncontradoException("Registro no encontrado");
	        }
	    } else {
	        log.error("Error: Usuario con ID " + usuarioId + " no encontrado.");
	        throw new UserNotFoundException("Usuario no encontrado");
	    }
	}



	private boolean esFechaDisponible(Fecha fecha, Alumno alumno) {
		return !registroPracticaRepository.existsByAlumnoAndFecha(alumno, fecha);
	}

}
