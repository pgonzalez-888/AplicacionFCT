package ceu.dam.javafx.proyectofct.gui;

import java.util.List;

import org.openapitools.client.api.UsuarioApiServiceApi;
import org.openapitools.client.model.RegistroPractica;
import org.openapitools.client.model.Usuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class PruebaPerfilController extends AppController {

	private UsuarioApiServiceApi service;
	private Usuario user;
	@FXML
	private Button btnConsultarDetalles;
	@FXML
	private Button cerrarBtn;

	@FXML
	private Label porcentajeLbl;

	@FXML
	private Label totalHorasLbl;

	@FXML
	private Label totalHorasPendientesLbl;

	@FXML
	private Label totalHorasRealizadasLbl;
	@FXML
	private AnchorPane panel;

	@FXML
	void irADetallesRegistro(ActionEvent event) {

		Parent nuevaVista = loadScene(FXML_CONSULTAR_REGISTRO);
		panel.getChildren().setAll(new Pane());
		panel.getChildren().setAll(nuevaVista);
	}

	@FXML
	void cerrar(ActionEvent event) {
		salir(event);
	}

	public void initialize() {
		service = new UsuarioApiServiceApi();
		user = new Usuario();
		user = (Usuario) getParam("usuario");
		List<RegistroPractica> registros = user.getUsuarioAsociado().getRegistrosPractica();
		Integer suma = 0;
		for (RegistroPractica registro : registros) {
			suma = suma + registro.getHoras();

		}

		Integer pendientes = Integer.parseInt(totalHorasLbl.getText()) - suma;
		Integer porcentaje = suma * 100 / 370;
		porcentajeLbl.setText(porcentajeLbl.getText() + porcentaje.toString() + "%");

		totalHorasRealizadasLbl.setText(totalHorasRealizadasLbl.getText() + suma.toString());
		totalHorasPendientesLbl.setText(totalHorasPendientesLbl.getText() + pendientes.toString());
	}
}
