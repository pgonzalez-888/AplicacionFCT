package ceu.dam.javafx.proyectofct.gui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.UsuarioApiServiceApi;
import org.openapitools.client.model.Fecha;
import org.openapitools.client.model.RegistroPractica;
import org.openapitools.client.model.Usuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
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
	private Pane panel;

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
			LocalDate fechaDesde = null;
			LocalDate fechaHasta = null;
			List<Fecha> fechas = service.consultarFechas();

			if (usuario.getUsuarioAsociado().getEvaluacion().equals("Marzo")) {
				fechaDesde = LocalDate.of((usuario.getUsuarioAsociado().getAnioCurso()), 3, 1);
				fechaHasta = LocalDate.of((usuario.getUsuarioAsociado().getAnioCurso()), 6, 1);
			} else {
				fechaDesde = LocalDate.of((usuario.getUsuarioAsociado().getAnioCurso()), 9, 1);
				fechaHasta = LocalDate.of((usuario.getUsuarioAsociado().getAnioCurso()), 12, 1);
			}

			List<RegistroPractica> registros = service.consultarRegistros(usuario.getId(), fechaDesde, fechaHasta,
					"completas");

			// Usamos una lista auxiliar para guardar las fechas no asociadas
			List<Fecha> fechasNoAsociadas = new ArrayList<>(fechas);

			for (RegistroPractica registroPractica : registros) {
				Iterator<Fecha> iterator = fechasNoAsociadas.iterator();
				while (iterator.hasNext()) {
					Fecha fecha = iterator.next();
					if (fecha.getFecha().equals(registroPractica.getFecha().getFecha())
							|| fecha.getFecha().isBefore(fechaDesde) || fecha.getFecha().isAfter(fechaHasta)) {
						iterator.remove(); // Elimina las fechas ya asociadas
					}
				}
			}

			// Después de remover las fechas asociadas, agregamos las no asociadas al
			// comboBox
			for (Fecha fechaNoAsociada : fechasNoAsociadas) {
				comboBoxFecha.getItems().add(fechaNoAsociada.getFecha());
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

		if (comboBoxFecha.getValue() == null || tfDescripcion.getText().isEmpty()
				|| tfNumeroHoras.getText().isEmpty()) {
			lanzarError("Todos los campos son obligatorios");
		} else {
			
	        // Validar que el campo de horas contenga un número válido
	        double horas;
	        try {
	            horas = Double.parseDouble(tfNumeroHoras.getText());

	            // Validar que las horas estén en el rango permitido y sean múltiplo de 0.5
	            if (horas <= 0 || horas > 8 || (horas * 10) % 5 != 0) {
	                lanzarError("Las horas deben ser un número entre 0.5 y 8, en incrementos de 0.5.");
	                return;
	            }
	        } catch (NumberFormatException e) {
	            lanzarError("Ingrese un número válido en el campo de horas.");
	            return;
	        }

			try {
				List<Fecha> fechas;
				fechas = service.consultarFechas();

				Iterator<Fecha> iterator = fechas.iterator();
				while (iterator.hasNext()) {
					Fecha fecha = iterator.next();
					if (!fecha.getFecha().equals(comboBoxFecha.getValue())) {
						iterator.remove(); // Elimina las fechas ya asociadas
					}
				}

				RegistroPractica registroNuevo = new RegistroPractica();
				registroNuevo.setFecha(fechas.get(0));
				registroNuevo.setAlumno(usuario.getUsuarioAsociado());
				registroNuevo.setDescripcion(tfDescripcion.getText());
				registroNuevo.setHoras(Integer.valueOf(tfNumeroHoras.getText()));

				service.altaRegistroPractica(usuario.getUsuarioAsociado().getId(), fechas.get(0).getId(),
						Integer.valueOf(tfNumeroHoras.getText()), tfDescripcion.getText());

				Parent nuevaVista = loadScene(FXML_CONSULTAR_REGISTRO);
				panel.getChildren().setAll(new Pane());
				panel.getChildren().setAll(nuevaVista);
			} catch (ApiException e) {
				lanzarError(e.getMessage());
			}
		}
	}

	@FXML
	void volver(ActionEvent event) {
		Parent nuevaVista = loadScene(FXML_CONSULTAR_REGISTRO);
		panel.getChildren().setAll(new Pane());
		panel.getChildren().setAll(nuevaVista);
	}

}
