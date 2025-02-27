package ceu.dam.javafx.proyectofct.gui;

import org.openapitools.client.ApiException;
import org.openapitools.client.api.UsuarioApiServiceApi;
import org.openapitools.client.model.Usuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;

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
	}
	
	@FXML
	void cambiarContraseña(ActionEvent event) {
		try {
			service.cambiarContrasena(usuario.getId(), oldPassPf.getText(), newPassPf.getText());
		} catch (ApiException e) {
			lanzarError(e.getMessage());
		}
	}
	
	public void lanzarError(String mensaje) {
		Alert alerta = new Alert(AlertType.ERROR);
		alerta.setTitle("Error");
		alerta.setHeaderText(null);
		alerta.setContentText(mensaje);
		alerta.showAndWait();
	}
}
