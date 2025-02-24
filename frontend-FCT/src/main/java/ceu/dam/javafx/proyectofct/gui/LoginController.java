package ceu.dam.javafx.proyectofct.gui;

import java.util.Optional;

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.UsuarioApiServiceApi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController extends AppController {

	private UsuarioApiServiceApi service;

	@FXML
	private Button cerrarAplicacionBtn;

	@FXML
	private PasswordField PasswordPassField;

	@FXML
	private Button btnIniciarSesion;

	@FXML
	private TextField tfUsername;

	@FXML
	void login(ActionEvent event) {
		try {
			service.login(tfUsername.getText(), PasswordPassField.getText());
		} catch (ApiException e) {

			e.printStackTrace();
		}
	}

	@FXML
	void salir(ActionEvent event) {
		Alert pregunta = new Alert(AlertType.CONFIRMATION);
		pregunta.setContentText("¿Estás seguro de querer cerrar?");
		pregunta.setTitle("Confirmación");
		pregunta.setHeaderText(null);
		Optional<ButtonType> respuesta = pregunta.showAndWait();
		if (respuesta.get() == ButtonType.OK) {
			System.exit(0);
		}

	}
	
	@FXML
	void initialize() {
		ApiClient cliente = new ApiClient();
		cliente.setApiKey("javiylasardillas");
		cliente.setBasePath("http://localhost:8080");
		service = new UsuarioApiServiceApi(cliente);
		
	}

}
