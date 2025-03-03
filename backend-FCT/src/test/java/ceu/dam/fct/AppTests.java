package ceu.dam.fct;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ceu.dam.fct.model.Usuario;
import ceu.dam.fct.repository.UsuarioRepository;
import ceu.dam.fct.service.UsuarioServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

@SpringBootTest
class AppTests {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @MockBean
    private UsuarioRepository usuarioRepository;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombreUsuario("testUser");
        usuario.setContrasena("password123");
        usuario.setPerfil("Alumno");
        usuario.setActivo(true);
    }

    @Test
    void testIniciarSesion_UsuarioExisteYActivo() {
        when(usuarioRepository.findByNombreUsuario("testUser")).thenReturn(Optional.of(usuario));

        Optional<Usuario> resultado = usuarioService.iniciarSesion("testUser", "password123");

        assertTrue(resultado.isPresent());
        assertEquals("testUser", resultado.get().getNombreUsuario());
    }

    @Test
    void testIniciarSesion_UsuarioNoExiste() {
        when(usuarioRepository.findByNombreUsuario("testUser")).thenReturn(Optional.empty());

        Optional<Usuario> resultado = usuarioService.iniciarSesion("testUser", "password123");

        assertFalse(resultado.isPresent());
    }

    @Test
    void testCambiarContrasena() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        boolean resultado = usuarioService.cambiarContrasena(1L, "newPassword");

        assertTrue(resultado);
        assertEquals("newPassword", usuario.getContrasena());
    }

    @Test
    void testActivarUsuario() {
        usuario.setActivo(false);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        boolean resultado = usuarioService.activarUsuario(1L);

        assertTrue(resultado);
        assertTrue(usuario.getActivo());
    }

    @Test
    void testBuscarPorNombre() {
        when(usuarioRepository.findByNombreUsuario("testUser")).thenReturn(Optional.of(usuario));

        Optional<Usuario> resultado = usuarioService.buscarPorNombre("testUser");

        assertTrue(resultado.isPresent());
        assertEquals("testUser", resultado.get().getNombreUsuario());
    }
}

