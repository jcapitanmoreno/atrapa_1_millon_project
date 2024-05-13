package com.github.jcapitanmoreno.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddQuestionController extends Controller implements Initializable {
    @FXML
    private TextField question;
    @FXML
    private TextField answer1;
    @FXML
    private TextField answer2;
    @FXML
    private TextField answer4;
    @FXML
    private Button add;

    @Override
    public void onOpen(Object input) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void addQuestion(){
        System.out.println("pepepepepepepep");

        //paso1= recoger la pregunta y añadirla a la base de datos a traves de su DAO
        //paso2= recoger el resultado y obtener el ID asignado a la pregunta en la base de datos
        //paso3= recoger las respuestas y pasarle el ID de la pregunta para añadirlo a la base de datos y vincularlo a la pregunta
    }

}
