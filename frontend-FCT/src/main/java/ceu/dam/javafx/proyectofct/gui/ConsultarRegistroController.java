package ceu.dam.javafx.proyectofct.gui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.UsuarioApiServiceApi;
import org.openapitools.client.model.Fecha;
import org.openapitools.client.model.RegistroPractica;
import org.openapitools.client.model.Usuario;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

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

	@FXML
	private Pane panel;

	private ObservableList<RegistroPractica> datos;

	@FXML
	public void initialize() {
		descripcionTColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		horaTColumn.setCellValueFactory(new PropertyValueFactory<>("horas"));
		fechaTColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));

		fechaTColumn
				.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getFecha().getFecha()));

		datos = FXCollections.observableArrayList();
		tabla.setItems(datos);

		usuario = (Usuario) getParam("usuario");
		ApiClient cliente = new ApiClient();
		cliente.setApiKey("javiylasardillas");
		cliente.setBasePath("http://localhost:8080");
		service = new UsuarioApiServiceApi(cliente);

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
				} else {
					fechaHasta = LocalDate.parse(fechaHastaTf.getText(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
				}
			} catch (DateTimeParseException e) {
				lanzarError("Las fechas deben tener el formato dd-MM-yyyy");
				return; // Detener ejecución si hay un error de formato
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
			List<Fecha> fechas = service.consultarFechas();

			// Filtrar las fechas según el rango indicado
			Iterator<Fecha> iterator = fechas.iterator();
			while (iterator.hasNext()) {
				Fecha fecha = iterator.next();
				if (fecha.getFecha().isBefore(fechaDesde) || fecha.getFecha().isAfter(fechaHasta)) {
					iterator.remove(); // Elimina de manera segura las fechas fuera del rango
				}
			}

			// Crear una lista temporal para los registros vacíos
			List<RegistroPractica> registrosTemporales = new ArrayList<>(registros);

			// Añadir registros vacíos solo si el filtro es "todas" o "sin_completar"
			if (filtro.equals("todas") || filtro.equals("sin_completar")) {
				for (Fecha fecha : fechas) {
					boolean encontrado = false;

					// Comprobar si ya existe un registro para esa fecha
					for (RegistroPractica registro : registros) {
						if (registro.getFecha().equals(fecha)) {
							encontrado = true;
							break; // Si ya existe, no añadir uno vacío
						}
					}

					// Si no se encontró un registro para esa fecha, agregar un registro vacío
					if (!encontrado) {
						RegistroPractica registroNuevo = new RegistroPractica();
						registroNuevo.setDescripcion("Vacío");
						registroNuevo.setFecha(fecha);
						registrosTemporales.add(registroNuevo);
					}
				}
			}

			// Configuración de la RowFactory para cambiar el color de las filas "vacías"
			tabla.setRowFactory(tv -> {
				TableRow<RegistroPractica> row = new TableRow<>();
				row.itemProperty().addListener((obs, oldItem, newItem) -> {
					if (newItem != null && newItem.getDescripcion().equals("Vacío")) {
						row.setStyle("-fx-background-color: #f5c255;");
					} else {
						row.setStyle("-fx-background-color: white;");
					}
				});
				return row;
			});
			// Actualizar la tabla con los datos obtenidos (registros existentes + registros
			// vacíos)
			datos.setAll(registrosTemporales);

		} catch (ApiException e) {
			e.printStackTrace();
			lanzarError("Error al consultar la API");
		}
	}

	@FXML
	void irADarAltaPantalla(ActionEvent event) {
		Parent nuevaVista = loadScene(FXML_ALTA_REGISTRO);
		panel.getChildren().setAll(new Pane());
		panel.getChildren().setAll(nuevaVista);
	}

	@FXML
	void verDetalle(MouseEvent event) {
		RegistroPractica registroSeleccionado = tabla.getSelectionModel().getSelectedItem();
		if (registroSeleccionado != null) {
			// Cambiar la pantalla y pasar el parámetro
			System.out.println(registroSeleccionado);
			addParam("registro", registroSeleccionado); // Pasar el registro
			Parent nuevaVista = loadScene(FXML_DETALLE_REGISTRO);
			panel.getChildren().setAll(new Pane());
			panel.getChildren().setAll(nuevaVista);
		}
	}

	@FXML
	void cerrar(ActionEvent event) {
		salir(event);
	}
}
