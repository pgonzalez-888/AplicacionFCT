/*
 * OpenAPI definition
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: v0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.api;

import org.openapitools.client.ApiException;
import org.openapitools.client.model.Fecha;
import java.time.LocalDate;
import org.openapitools.client.model.RegistroPractica;
import org.openapitools.client.model.Usuario;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for UsuarioApiServiceApi
 */
@Disabled
public class UsuarioApiServiceApiTest {

    private final UsuarioApiServiceApi api = new UsuarioApiServiceApi();

    /**
     * Registro alta de los usuarios al incorporarse a las prácticas
     *
     * Registro alta usuarios
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void altaRegistroPracticaTest() throws ApiException {
        Long id = null;
        Long fechaId = null;
        Integer horas = null;
        String detalle = null;
        api.altaRegistroPractica(id, fechaId, horas, detalle);
        // TODO: test validations
    }

    /**
     * Borrar registro alumno
     *
     * Borrar registro
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void borrarRegistroPracticaTest() throws ApiException {
        Long id = null;
        Long registroId = null;
        api.borrarRegistroPractica(id, registroId);
        // TODO: test validations
    }

    /**
     * Cambiar contraseña de usuarios
     *
     * Cambiar contraseña a un usuario
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void cambiarContrasenaTest() throws ApiException {
        Long id = null;
        String antiguaContrasena = null;
        String nuevaContrasena = null;
        api.cambiarContrasena(id, antiguaContrasena, nuevaContrasena);
        // TODO: test validations
    }

    /**
     * Consultar fechas existentes
     *
     * Consultar fechas que existan en la base de datos
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void consultarFechasTest() throws ApiException {
        List<Fecha> response = api.consultarFechas();
        // TODO: test validations
    }

    /**
     * Consultar registros del usuario
     *
     * Consultar registros del usuario
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void consultarRegistrosTest() throws ApiException {
        Long id = null;
        LocalDate fechaDesde = null;
        LocalDate fechaHasta = null;
        String filtro = null;
        List<RegistroPractica> response = api.consultarRegistros(id, fechaDesde, fechaHasta, filtro);
        // TODO: test validations
    }

    /**
     * Loguea al usuario
     *
     * Loguea al usuario
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void loginTest() throws ApiException {
        String nombreUsuario = null;
        String password = null;
        Usuario response = api.login(nombreUsuario, password);
        // TODO: test validations
    }

}
