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
     * Muestra una alerta de información con el título, el encabezado y el contenido especificados.
     *
     * @param text1 El título de la alerta.
     * @param text2 El texto del encabezado de la alerta.
     * @param text3 El contenido de la alerta.
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
     * Muestra una alerta de información con un mensaje predefinido.
     * Esta alerta proporciona información sobre las restricciones del juego.
     */
    public void infoAlert() {
        informationAlert("INFORMACIÓN AÑADIDA","MAS INFORMACION","Una vez empiezas a jugar no puedes volver atras, termina de jugar o cierra el programa. Gracias por su atención.");
    }
    /**
     * Cambia la escena actual a la escena del juego.
     *
     * @throws IOException Si hay un error al cambiar a la escena del juego.
     */
    public void changeSceneToGame() throws IOException {
        App.currentController.changeScene(Scenes.GAMEGAME, null);
    }
}
