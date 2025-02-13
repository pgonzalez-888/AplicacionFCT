package ceu.dam.fct.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ceu.dam.fct.model.RegistroPractica;
import ceu.dam.fct.model.Usuario;
import ceu.dam.fct.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "Authorization")
public class UsuarioApiService {

	@Autowired
	private UsuarioService service;

	@GetMapping("/login")
	@Operation(summary = "Loguea al usuario", description = "Loguea al usuario")
	public Usuario login(@RequestParam @NotBlank String nombreUsuario, @RequestParam @NotBlank String password)
			throws UserNotFoundException, UserUnauthorizedException, UserException {
		return service.login(nombreUsuario, password);
	}

	@PutMapping("/password")
	@Operation(summary = "Cambiar contraseña de usuarios", description = "Cambiar contraseña a un usuario")
	public void cambiarContrasena(@RequestParam String nuevaContrasena, @RequestParam String usuarioLogado) {
		service.cambiarContrasena(nuevaContrasena, usuarioLogado);
	}

	@GetMapping("/consultarRegistros")
	@Operation(summary = "Consultar registros del usuario", description = "Consultar registros del usuario")
	public List<RegistroPractica> consultarRegistros(@RequestParam String usuarioLogado,
			@RequestParam String fechaDesde, @RequestParam String fechaHasta, @RequestParam String filtro) {
		return service.consultarRegistros(usuarioLogado, fechaDesde, fechaHasta, filtro);
	}

	@PutMapping("/altaPractica")
	@Operation(summary = "Registro alta de los usuarios al incorporarse a las prácticas", description = "Registro alta usuarios")
	public void altaRegistroPractica(@RequestParam String usuarioLogado, @RequestParam Integer fechaId,
			@RequestParam Integer horas, @RequestParam String detalle) {
		service.altaRegistroPractica(usuarioLogado, fechaId, horas, detalle);
	}

	@DeleteMapping("/borrarRegistro")
	@Operation(summary = "Borrar registro alumno", description = "Borrar registro")
	public void borrarRegistroPractica(@RequestParam Integer registroId, @RequestParam String usuarioLogado) {
		service.borrarRegistroPractica(registroId, usuarioLogado);
	}
}
