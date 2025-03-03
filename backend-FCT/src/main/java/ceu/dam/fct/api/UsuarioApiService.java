package ceu.dam.fct.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ceu.dam.fct.model.Fecha;
import ceu.dam.fct.model.RegistroPractica;
import ceu.dam.fct.model.Usuario;
import ceu.dam.fct.service.FechaNoDisponibleException;
import ceu.dam.fct.service.PasswordChangeException;
import ceu.dam.fct.service.RegistroNoEncontradoException;
import ceu.dam.fct.service.UserNotFoundException;
import ceu.dam.fct.service.UserUnauthorizedException;
import ceu.dam.fct.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "Authorization")
public class UsuarioApiService {

	@Autowired
	private UsuarioService service;

	@GetMapping("/login")
	@Operation(summary = "Loguea al usuario", description = "Loguea al usuario")
	public Usuario login(@RequestParam String nombreUsuario, @RequestParam String password)
			throws UserNotFoundException, UserUnauthorizedException {
		return service.login(nombreUsuario, password);
	}

	@PutMapping("/password/{id}")
	@Operation(summary = "Cambiar contraseña de usuarios", description = "Cambiar contraseña a un usuario")
	public void cambiarContrasena(@PathVariable Long id, @RequestParam String antiguaContrasena,
			@RequestParam String nuevaContrasena)
			throws PasswordChangeException, UserUnauthorizedException, UserNotFoundException {
		service.cambiarContrasena(id, antiguaContrasena, nuevaContrasena);
	}

	@GetMapping("/consultarRegistros/{id}")
	@Operation(summary = "Consultar registros del usuario", description = "Consultar registros del usuario")
	public List<RegistroPractica> consultarRegistros(@PathVariable Long id,
			@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fechaDesde,
			@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fechaHasta, @RequestParam String filtro)
			throws UserNotFoundException {
		return service.consultarRegistros(id, fechaDesde, fechaHasta, filtro);
	}

	@GetMapping("/consultarFechas")
	@Operation(summary = "Consultar fechas existentes", description = "Consultar fechas que existan en la base de datos")
	public List<Fecha> consultarFechas() {
		return service.consultarFechas();
	}

	@PutMapping("/altaPractica/{id}")
	@Operation(summary = "Registro alta de los usuarios al incorporarse a las prácticas", description = "Registro alta usuarios")
	public void altaRegistroPractica(@PathVariable Long id, @RequestParam Long fechaId, @RequestParam Integer horas,
			@RequestParam String detalle) throws UserNotFoundException, FechaNoDisponibleException {
		service.altaRegistroPractica(id, fechaId, horas, detalle);
	}

	@DeleteMapping("/borrarRegistro/{id}")
	@Operation(summary = "Borrar registro alumno", description = "Borrar registro")
	public void borrarRegistroPractica(@PathVariable Long id, @RequestParam Long registroId)
			throws UserNotFoundException, RegistroNoEncontradoException {
		service.borrarRegistroPractica(id, registroId);
	}
}
