package ceu.dam.javafx.proyectofct.gui;

import org.apache.commons.codec.digest.DigestUtils;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.UsuarioApiServiceApi;
import org.openapitools.client.model.Usuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class LoginController extends AppController {

	private UsuarioApiServiceApi service;

	@FXML
	private PasswordField PasswordPassField;

	@FXML
	private Button cerrarBtn;

	@FXML
	private Button btnIniciarSesion;

	@FXML
	private TextField tfUsername;

	@FXML
	void login(ActionEvent event) {
		try {
			String passwordCipher = DigestUtils.sha256Hex(PasswordPassField.getText());
			Usuario usuario = service.login(tfUsername.getText(), passwordCipher);
			addParam("usuario", usuario);
			changeScene(FXML_CON_MENU);
		} catch (ApiException e) {
			lanzarError(e.getMessage());
		}
	}

	@FXML
	void cerrarAplicacion(ActionEvent event) {
		salir(event);
	}

	public void initialize() {
		ApiClient cliente = new ApiClient();
		cliente.setApiKey("javiylasardillas");
		cliente.setBasePath("http://localhost:8080");
		service = new UsuarioApiServiceApi(cliente);
	}
}
