package ceu.dam.javafx.proyectofct.gui;

import org.openapitools.client.model.Usuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MenuController extends AppController {

	private Usuario user;

	@FXML
	private Button btnCambiarContraseña;

	@FXML
	private Button btnCerrarSesion;

	@FXML
	private Button btnIraPerfil;

	@FXML
	private Label cicloLbl;

	@FXML
	private Label empresaLbl;

	@FXML
	private Label evaluacionLbl;

	@FXML
	private Pane menuPanel;

	@FXML
	private Label nombreCompleto;

	@FXML
	private BorderPane panelCambiante;

	@FXML
	private Pane panelPrincipal;

	@FXML
	private Label tutorLbl;

	@FXML
	void cambiarContraseñaView(ActionEvent event) {
		Parent nuevaVista = loadScene(FXML_CAMBIAR_CONTRASEÑA);

		panelCambiante.setCenter(new Pane());
		panelCambiante.setCenter(nuevaVista);

	}

	@FXML
	void cerrarSesion(ActionEvent event) {
		changeScene(FXML_LOGIN);

	}

	@FXML
	void irAPerfilView(ActionEvent event) {

		Parent nuevaVista = loadScene(FXML_PERFIL_PRUEBA);

		panelCambiante.setCenter(new Pane());
		panelCambiante.setCenter(nuevaVista);

	}

	public void initialize() {
		Parent nuevaVista = loadScene(FXML_PERFIL_PRUEBA);

		panelCambiante.setCenter(new Pane());
		panelCambiante.setCenter(nuevaVista);

		user = new Usuario();
		user = (Usuario) getParam("usuario");
		cicloLbl.setText(user.getUsuarioAsociado().getCiclo());
		empresaLbl.setText(user.getUsuarioAsociado().getEmpresa().getNombreEmpresa());
		evaluacionLbl.setText(user.getUsuarioAsociado().getEvaluacion());
		nombreCompleto.setText(user.getUsuarioAsociado().getNombreCompleto());

	}

}
