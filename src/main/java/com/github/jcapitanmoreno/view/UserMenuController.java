package com.github.jcapitanmoreno.view;

import com.github.jcapitanmoreno.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserMenuController extends Controller implements Initializable {
    @FXML
    private Button play;
    @FXML
    private Button gestor;

    @Override
    public void onOpen(Object input) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void changeEsceneToGestorUser() throws IOException {
        App.currentController.changeScene(Scenes.USERGESTOR, null);

    }
    public void changeEsceneToPlayUser(){

    }
}
