package ceu.dam.javafx.proyectofct.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DarAltaController extends AppController{
	@FXML
    private Button btnDarDeAlta;

    @FXML
    private ComboBox<?> comboBoxFecha;

    @FXML
    private TextArea tfDescripcion;

    @FXML
    private TextField tfNumeroHoras;

    @FXML
    void darDeAlta(ActionEvent event) {

    }
}
