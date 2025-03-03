package ceu.dam.javafx.proyectofct.gui;

import java.util.Optional;

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.UsuarioApiServiceApi;
import org.openapitools.client.model.RegistroPractica;
import org.openapitools.client.model.Usuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class DetalleRegistroController extends AppController {

	private UsuarioApiServiceApi service;
	private RegistroPractica registro;
	private Usuario user;
	@FXML
	private Button btnBorrarRegistro;

	@FXML
	private Pane panel;

	@FXML
	private Button cerrarBtn;

	@FXML
	private Label descripcionLbl;

	@FXML
	private Label fechaRegistroLbl;

	@FXML
	private Label numeroHorasLbl;

	public void initialize() {
		ApiClient cliente = new ApiClient();
		cliente.setApiKey("javiylasardillas");
		cliente.setBasePath("http://localhost:8080");
		service = new UsuarioApiServiceApi(cliente);

		user = new Usuario();
		user = (Usuario) getParam("usuario");
		registro = new RegistroPractica();
		registro = (RegistroPractica) getParam("registro");
		descripcionLbl.setText(descripcionLbl.getText() + registro.getDescripcion());
		fechaRegistroLbl.setText(fechaRegistroLbl.getText() + registro.getFecha().getFecha().toString());
		numeroHorasLbl.setText(numeroHorasLbl.getText() + registro.getHoras().toString());

	}

	@FXML
	void borrarRegistro(ActionEvent event) {
		try {
			Alert pregunta = new Alert(AlertType.CONFIRMATION);
			pregunta.setContentText("¿Estás seguro de querer borrar el registro?");
			pregunta.setTitle("Confirmación");
			pregunta.setHeaderText(null);
			Optional<ButtonType> respuesta = pregunta.showAndWait();
			if (respuesta.get() == ButtonType.OK) {
				service.borrarRegistroPractica(user.getId(), registro.getId());
				
				Parent nuevaVista = loadScene(FXML_CONSULTAR_REGISTRO);
				panel.getChildren().setAll(new Pane());
				panel.getChildren().setAll(nuevaVista);
			}
		} catch (ApiException e) {
			lanzarError(e.getMessage());
		}

	}

	@FXML
	void cerrar(ActionEvent event) {
		salir(event);
	}

	@FXML
	void volver(ActionEvent event) {
		Parent nuevaVista = loadScene(FXML_CONSULTAR_REGISTRO);
		panel.getChildren().setAll(new Pane());
		panel.getChildren().setAll(nuevaVista);
	}
}
