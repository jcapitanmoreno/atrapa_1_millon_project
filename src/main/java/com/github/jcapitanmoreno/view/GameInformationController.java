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
    /**
     * Displays an information alert dialog.
     *
     * @param text1 The title of the alert dialog.
     * @param text2 The header text of the alert dialog.
     * @param text3 The content text of the alert dialog.
     */
    @Override
    public void informationAlert(String text1, String text2, String text3) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(text1);
        alert.setHeaderText(text2);
        alert.setContentText(text3);
        alert.show();
    }

    @Override
    public void errorAlert(String text1, String text2, String text3) {

    }

    @Override
    public void warningAlert(String text1, String text2, String text3) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    /**
     * Displays an information alert dialog with specific content.
     */
    public void infoAlert() {
        informationAlert("INFORMACIÓN AÑADIDA","MAS INFORMACION","Una vez empiezas a jugar no puedes volver atras, termina de jugar o cierra el programa. Gracias por su atención.");
    }
    /**
     * Changes the scene to the game scene.
     *
     * @throws IOException If an I/O exception occurs.
     */
    public void changeSceneToGame() throws IOException {
        App.currentController.changeScene(Scenes.GAMEGAME, null);
    }
}
