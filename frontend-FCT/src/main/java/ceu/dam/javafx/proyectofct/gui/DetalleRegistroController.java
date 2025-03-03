package ceu.dam.javafx.proyectofct.gui;

import org.openapitools.client.ApiException;
import org.openapitools.client.api.UsuarioApiServiceApi;
import org.openapitools.client.model.RegistroPractica;
import org.openapitools.client.model.Usuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DetalleRegistroController extends AppController {

	private UsuarioApiServiceApi service;
	private RegistroPractica registro;
	private Usuario user;
	@FXML
	private Button btnBorrarRegistro;

	@FXML
	private Button cerrarBtn;

	@FXML
	private Label descripcionLbl;

	@FXML
	private Label fechaRegistroLbl;

	@FXML
	private Label numeroHorasLbl;

	public void initialize() {
		user = new Usuario();
		user = (Usuario) getParam("usuario");
		registro = new RegistroPractica();
		registro = (RegistroPractica) getParam("registro");
		descripcionLbl.setText(registro.getDescripcion());
		fechaRegistroLbl.setText(registro.getFecha().toString());
		numeroHorasLbl.setText(registro.getHoras().toString());

	}

	@FXML
	void borrarRegistro(ActionEvent event) {
		try {
			service.borrarRegistroPractica(user.getId(), registro.getId());
		} catch (ApiException e) {
			lanzarError(e.getMessage());
		}

	}

	@FXML
	void cerrar(ActionEvent event) {
		salir(event);

	}
}
