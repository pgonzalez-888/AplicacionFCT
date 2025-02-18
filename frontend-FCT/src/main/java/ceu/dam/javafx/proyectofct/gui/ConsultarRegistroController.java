package ceu.dam.javafx.proyectofct.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ConsultarRegistroController extends AppController{
	 @FXML
	    private Button btnConsultarFiltros;

	    @FXML
	    private Button btnDarDeAlta;

	    @FXML
	    private ComboBox<?> comboBoxOpciones;

	    @FXML
	    private TableColumn<?, ?> descripcionTColumn;

	    @FXML
	    private TextField fechaDesdeTf;

	    @FXML
	    private TextField fechaHastaTf;

	    @FXML
	    private TableColumn<?, ?> fechaTColumn;

	    @FXML
	    private TableColumn<?, ?> horaTColumn;

	    @FXML
	    private TableView<?> tabla;

	    @FXML
	    void consultarFiltros(ActionEvent event) {
	    	
	    }

	    @FXML
	    void irADarAltaPantalla(ActionEvent event) {
	    	
	    }
}
