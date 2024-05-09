package com.github.jcapitanmoreno.view;

import com.github.jcapitanmoreno.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UsersNameController extends Controller implements Initializable {
    @FXML
    private TextField name;
    @FXML
    private Button entrar;

    private Circle secretAdminEnter;

    @FXML
    public void changeToADM() throws IOException {
        App.currentController.changeScene(Scenes.ADMLOGIN,null);
    }

    @Override
    public void onOpen(Object input) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
