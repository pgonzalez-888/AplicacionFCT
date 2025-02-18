package ceu.dam.javafx.proyectofct.gui;

import org.openapitools.client.ApiException;
import org.openapitools.client.api.UsuarioApiServiceApi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
			service.login(tfUsername.getText(), PasswordPassField.getText());
		} catch (ApiException e) {
			
			e.printStackTrace();
		}
    }

}
