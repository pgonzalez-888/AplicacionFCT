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
		//897
		//653
		
		primaryStage.setWidth(877);  // Ancho
	    primaryStage.setHeight(643); // Alto
	    
		controller.changeScene(AppController.FXML_LOGIN);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.show();

	}

}
