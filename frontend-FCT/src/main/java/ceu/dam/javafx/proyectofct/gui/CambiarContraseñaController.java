package ceu.dam.javafx.proyectofct.gui;

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.UsuarioApiServiceApi;
import org.openapitools.client.model.Usuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

public class CambiarContraseñaController extends AppController {

	private UsuarioApiServiceApi service;

	private Usuario usuario;

	@FXML
	private Button btnCambiarContraseña;

	@FXML
	private PasswordField newPassPf;

	@FXML
	private PasswordField oldPassPf;

	@FXML
	private PasswordField repeatNewPassPf;

	public void initialize() {
		usuario = (Usuario) getParam("usuario");
		ApiClient cliente = new ApiClient();
		cliente.setApiKey("javiylasardillas");
		cliente.setBasePath("http://localhost:8080");
		service = new UsuarioApiServiceApi(cliente);

	}

	@FXML
	void cambiarContraseña(ActionEvent event) {
		try {
			service.cambiarContrasena(usuario.getId(), oldPassPf.getText(), newPassPf.getText());
		} catch (ApiException e) {
			lanzarError(e.getMessage());
		}
	}

	@FXML
	void cerrar(ActionEvent event) {
		salir(event);
	}
}
