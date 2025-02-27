package ceu.dam.javafx.proyectofct.gui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import ceu.dam.javafx.proyectofct.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class AppController {

	private static final String PATH_BASE = "/fxml/";
	public static final String FXML_LOGIN = PATH_BASE + "login.fxml";
	public static final String FXML_CON_MENU = PATH_BASE + "menu.fxml";
	public static final String FXML_SIN_MENU = PATH_BASE + "sinmenu.fxml";
	public static final String FXML_CAMBIAR_CONTRASEÑA = PATH_BASE + "cambiarcontraseña.fxml";
	public static final String FXML_CONSULTAR_REGISTRO = PATH_BASE + "consultarregistro.fxml";
	public static final String FXML_RESUMEN_HORAS = PATH_BASE + "resumenhoras.fxml";
	public static final String FXML_DETALLE_REGISTRO = PATH_BASE + "detalleRegistro.fxml";
	public static final String FXML_ALTA_REGISTRO = PATH_BASE + "dardealtaregistro.fxml";

	protected static Stage primaryStage;

	public AppController() {

	}

	public AppController(Stage stage) {
		primaryStage = stage;
	}

	public AppController changeScene(String fxml) {
		try {
			FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml));
			Scene scene = new Scene(loader.load());
			primaryStage.setScene(scene);
			return loader.getController();
		} catch (IOException e) {
			throw new RuntimeException("Error cambiando escena.", e);
		}
	}

	public Parent loadScene(String fxml) {
		try {
			FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml));
			Scene scene = new Scene(loader.load());
			return scene.getRoot();
		} catch (IOException e) {
			throw new RuntimeException("Error cambiando escena.", e);
		}
	}

	public void addParam(String key, Object param) {
		Map<String, Object> mapa = (Map<String, Object>) primaryStage.getUserData();
		if (mapa == null) {
			mapa = new HashMap<String, Object>();
			primaryStage.setUserData(mapa);
		}
		mapa.put(key, param);
	}

	public Object getParam(String key) {
		Map<String, Object> mapa = (Map<String, Object>) primaryStage.getUserData();
		return mapa.get(key);
	}

	public void lanzarError(String mensaje) {
		Alert alerta = new Alert(AlertType.ERROR);
		alerta.setTitle("Error");
		alerta.setHeaderText(null);
		alerta.setContentText(mensaje);
		alerta.showAndWait();
	}

	public void salir(ActionEvent event) {
		Alert pregunta = new Alert(AlertType.CONFIRMATION);
		pregunta.setContentText("¿Estás seguro de querer cerrar?");
		pregunta.setTitle("Confirmación");
		pregunta.setHeaderText(null);
		Optional<ButtonType> respuesta = pregunta.showAndWait();
		if (respuesta.get() == ButtonType.OK) {
			System.exit(0);
		}
	}
}
