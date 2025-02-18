package ceu.dam.javafx.proyectofct.gui;

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

public class LoginController extends AppController{
	
	private UsuarioApiServiceApi service;

    @FXML
    private PasswordField PasswordPassField;

    @FXML
    private Button btnIniciarSesion;

    @FXML
    private TextField tfUsername;

    @FXML
    void login(ActionEvent event) {
    	try {
			Usuario usuario=service.login(tfUsername.getText(), PasswordPassField.getText());
			addParam("usuario",usuario);
			changeScene(FXML_CON_MENU);
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
