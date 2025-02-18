package ceu.dam.javafx.proyectofct;

import ceu.dam.javafx.proyectofct.gui.AppController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.initStyle(StageStyle.UNDECORATED);
		AppController controller = new AppController(primaryStage);
		
		primaryStage.setWidth(800);  // Ancho
	    primaryStage.setHeight(600); // Alto
	    
		controller.changeScene(AppController.FXML_LOGIN);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.show();

	}

}
