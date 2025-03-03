package ceu.dam.javafx.proyectofct.gui;

import java.time.LocalDate;
import java.util.List;

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.UsuarioApiServiceApi;
import org.openapitools.client.model.Fecha;
import org.openapitools.client.model.RegistroPractica;
import org.openapitools.client.model.Usuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DarAltaController extends AppController {

	private UsuarioApiServiceApi service;

	private RegistroPractica registro;

	private Usuario usuario;

	@FXML
	private Button btnDarDeAlta;
	@FXML
	private Button cerrarBtn;

	@FXML
	private ComboBox<LocalDate> comboBoxFecha;

	@FXML
	private TextArea tfDescripcion;

	@FXML
	private TextField tfNumeroHoras;

	public void initialize() {
		usuario = (Usuario) getParam("usuario");



		ApiClient cliente = new ApiClient();
		cliente.setApiKey("javiylasardillas");
		cliente.setBasePath("http://localhost:8080");
		service = new UsuarioApiServiceApi(cliente);

		try {
			List<RegistroPractica> registros = service.consultarRegistros(usuario.getId(), LocalDate.of(01, 01, 2000),
					LocalDate.of(01, 01, 2050), "sin_completar");

			for (RegistroPractica registroPractica : registros) {
				comboBoxFecha.getItems().add(registroPractica.getFecha().getFecha());
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
	void darDeAlta(ActionEvent event) {
		Fecha fecha = new Fecha();
		fecha.setFecha(comboBoxFecha.getValue());
		fecha.setAnioCurso(comboBoxFecha.getValue().getYear());
		if (comboBoxFecha.getValue().getMonthValue() <= 6) {
			fecha.setEvaluacion("Marzo");
		} else {
			fecha.setEvaluacion("Septiembre");
		}
		registro = new RegistroPractica();
		registro.setAlumno(usuario.getUsuarioAsociado());
		registro.setDescripcion(tfDescripcion.getText());
		registro.setFecha(fecha);
		registro.setHoras(Integer.valueOf(tfNumeroHoras.getText()));
	}

	public void lanzarError(String mensaje) {
		Alert alerta = new Alert(AlertType.ERROR);
		alerta.setTitle("Error");
		alerta.setHeaderText(null);
		alerta.setContentText(mensaje);
		alerta.showAndWait();
	}
}
