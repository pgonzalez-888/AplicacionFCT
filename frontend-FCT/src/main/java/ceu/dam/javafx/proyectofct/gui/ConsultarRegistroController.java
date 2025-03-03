package ceu.dam.javafx.proyectofct.gui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.UsuarioApiServiceApi;
import org.openapitools.client.model.RegistroPractica;
import org.openapitools.client.model.Usuario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ConsultarRegistroController extends AppController {

	private UsuarioApiServiceApi service;

	private Usuario usuario;
	@FXML
	private Button btnConsultarFiltros;

	@FXML
	private Button btnDarDeAlta;

	@FXML
	private ComboBox<String> comboBoxOpciones;

	@FXML
	private TableColumn<RegistroPractica, String> descripcionTColumn;

	@FXML
	private TextField fechaDesdeTf;

	@FXML
	private TextField fechaHastaTf;

	@FXML
	private TableColumn<RegistroPractica, LocalDate> fechaTColumn;

	@FXML
	private TableColumn<RegistroPractica, Integer> horaTColumn;

	@FXML
	private TableView<RegistroPractica> tabla;

	private ObservableList<RegistroPractica> datos;

	@FXML
	public void initialize() {
		descripcionTColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		fechaTColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		horaTColumn.setCellValueFactory(new PropertyValueFactory<>("horas"));

		datos = FXCollections.observableArrayList();
		tabla.setItems(datos);

		// usuario = (Usuario) getParam("usuario");
		ApiClient cliente = new ApiClient();
		cliente.setApiKey("javiylasardillas");
		cliente.setBasePath("http://localhost:8080");
		service = new UsuarioApiServiceApi(cliente);

		try {
			usuario = service.login("bakambu", "cacotagrande");
		} catch (ApiException e) {
			e.printStackTrace();
		}

		comboBoxOpciones.getItems().addAll("Completadas", "Sin completar", "Todas");
	}

	@FXML
	void consultarFiltros(ActionEvent event) {
		try {
			LocalDate fechaDesde = null;
			LocalDate fechaHasta = null;

			try {
			// Verificar si el campo de fechaDesde está vacío
			if (fechaDesdeTf.getText().isEmpty()) {
				if (usuario.getUsuarioAsociado().getEvaluacion().equals("Marzo")) {
					fechaDesde = LocalDate.of((usuario.getUsuarioAsociado().getAnioCurso()), 3, 1);
				} else {
					fechaDesde = LocalDate.of((usuario.getUsuarioAsociado().getAnioCurso()), 9, 1);
				}
			} else {
				fechaDesde = LocalDate.parse(fechaDesdeTf.getText(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			}

			// Verificar si el campo de fechaHasta está vacío
			if (fechaHastaTf.getText().isEmpty()) {
				if (usuario.getUsuarioAsociado().getEvaluacion().equals("Marzo")) {
					fechaHasta = LocalDate.of((usuario.getUsuarioAsociado().getAnioCurso()), 6, 1);
				} else {
					fechaHasta = LocalDate.of((usuario.getUsuarioAsociado().getAnioCurso()), 12, 1);
				}
			}
			} catch (DateTimeParseException e) {
				lanzarError("Las fechas deben tener el formato dd-MM-yyyy");
			}

			// Obtener el filtro seleccionado en el ComboBox
			String filtroSeleccionado = comboBoxOpciones.getValue();
			String filtro = "todas"; // Valor por defecto

			if (filtroSeleccionado != null) {
				switch (filtroSeleccionado) {
				case "Completadas":
					filtro = "completadas";
					break;
				case "Sin completar":
					filtro = "sin_completar";
					break;
				case "Todas":
					filtro = "todas";
					break;
				}
			}

			// Llamar a la API con los filtros aplicados
			List<RegistroPractica> registros = service.consultarRegistros(usuario.getId(), fechaDesde, fechaHasta,
					filtro);

			// Actualizar la tabla con los datos obtenidos
			datos.setAll(registros);
		} catch (ApiException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void irADarAltaPantalla(ActionEvent event) {

	}
}
