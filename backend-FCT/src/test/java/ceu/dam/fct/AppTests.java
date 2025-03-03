package ceu.dam.fct;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ActiveProfiles;

import ceu.dam.fct.model.Alumno;
import ceu.dam.fct.model.Fecha;
import ceu.dam.fct.model.Usuario;
import ceu.dam.fct.repository.AlumnoRepository;
import ceu.dam.fct.repository.FechaRepository;
import ceu.dam.fct.repository.RegistroPracticaRepository;
import ceu.dam.fct.repository.UsuarioRepository;
import ceu.dam.fct.service.PasswordChangeException;
import ceu.dam.fct.service.UserNotFoundException;
import ceu.dam.fct.service.UserUnauthorizedException;
import ceu.dam.fct.service.UsuarioServiceImpl;

@ActiveProfiles("test")
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AppTests {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private AlumnoRepository alumnoRepository;

	@Autowired
	private RegistroPracticaRepository registroPracticaRepository;

	@Autowired
	private FechaRepository fechaRepository;

	@Autowired
	private UsuarioServiceImpl usuarioService;

	@BeforeEach
	void setUp() {
	}

	@Test
	@Order(1)
	void testLogin_UsuarioExistente_CredencialesCorrectas() {
		Usuario usuario = new Usuario();
		usuario.setNombreUsuario("testuser");
		usuario.setContrasena("password123");
		usuario.setActivo(true);
		usuario = usuarioRepository.save(usuario);

		Usuario result;
		try {
			result = usuarioService.login("testuser", "password123");
			assertNotNull(result);
			assertEquals("testuser", result.getNombreUsuario());
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		} catch (UserUnauthorizedException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(2)
	void testLogin_UsuarioNoExistente() {
		assertThrows(UserNotFoundException.class, () -> usuarioService.login("usuario_inexistente", "password"));
	}

	@Test
	@Order(3)
	void testLogin_ContrasenaIncorrecta() {
		Usuario usuario = new Usuario();
		usuario.setNombreUsuario("testuser");
		usuario.setContrasena("password123");
		usuario.setActivo(true);
		usuarioRepository.save(usuario);

		assertThrows(UserUnauthorizedException.class, () -> usuarioService.login("testuser", "wrongpassword"));
	}

	@Test
	@Order(4)
	void testCambiarContrasena_Exitosa() {
		Usuario usuario = new Usuario();
		usuario.setNombreUsuario("testuser");
		usuario.setContrasena("oldpass123");
		usuario.setActivo(true);
		usuario = usuarioRepository.save(usuario);

		try {
			usuarioService.cambiarContrasena(usuario.getId(), "oldpass123", "newpass456");
		} catch (PasswordChangeException e) {
			e.printStackTrace();
		} catch (UserUnauthorizedException e) {
			e.printStackTrace();
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}

		Usuario actualizado = usuarioRepository.findById(usuario.getId()).orElseThrow();
		assertEquals("newpass456", actualizado.getContrasena());
	}

	@Test
	@Order(5)
	void testCambiarContrasena_Incorrecta() {
		Usuario usuario = new Usuario();
		usuario.setNombreUsuario("testuser");
		usuario.setContrasena("oldpass123");
		usuario.setActivo(true);
		usuarioRepository.save(usuario);

		assertThrows(UserUnauthorizedException.class,
				() -> usuarioService.cambiarContrasena(usuario.getId(), "wrongpass", "newpass456"));
	}

	@Test
	@Order(6)
	void testObtenerDatosAlumno_Exitoso() {
		Usuario usuario = new Usuario();
		usuario.setNombreUsuario("testuser");
		usuario.setContrasena("password123");
		usuario.setActivo(true);

		Alumno alumno = new Alumno();
		alumno.setCiclo("DAM");
		alumno.setEvaluacion("Marzo");
		alumno.setAnioCurso(2024);
		usuario.setUsuarioAsociado(alumno);

		usuario = usuarioRepository.save(usuario);
		alumno = alumnoRepository.save(alumno);

		Alumno result;
		try {
			result = usuarioService.obtenerDatosAlumno(usuario.getId());
			assertNotNull(result);
			assertEquals("DAM", result.getCiclo());
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(7)
	void testConsultarFechas() {
		Fecha fecha1 = new Fecha();
		fecha1.setFecha(LocalDate.of(2024, 3, 1));
		fecha1.setAnioCurso(2024);
		fecha1.setEvaluacion("Marzo");

		Fecha fecha2 = new Fecha();
		fecha2.setFecha(LocalDate.of(2024, 3, 2));
		fecha2.setAnioCurso(2024);
		fecha2.setEvaluacion("Marzo");

		fechaRepository.save(fecha1);
		fechaRepository.save(fecha2);

		List<Fecha> fechas = usuarioService.consultarFechas();
		assertEquals(2, fechas.size());
	}
}