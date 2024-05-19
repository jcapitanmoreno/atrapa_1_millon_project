package com.github.jcapitanmoreno.view;

import com.github.jcapitanmoreno.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameInformationController extends Controller implements Initializable {
    @FXML
    private Button start;
    @FXML
    private ImageView info;

    @Override
    public void onOpen(Object input) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void infoAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFORMACIÓN AÑADIDA");
        alert.setHeaderText("MAS INFORMACION");
        alert.setContentText("Una vez empiezas a jugar no puedes volver atras, termina de jugar o cierra el programa." +
                "Gracias por su atención.");
        alert.show();
    }

    public void changeSceneToGame() throws IOException {
        App.currentController.changeScene(Scenes.GAMEGAME, null);
    }
}
