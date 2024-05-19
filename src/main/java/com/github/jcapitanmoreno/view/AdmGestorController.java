package com.github.jcapitanmoreno.view;

import com.github.jcapitanmoreno.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdmGestorController extends Controller implements Initializable {
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
    public void informationAlert(String text1, String text2, String text3) {

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
     * Cambia la escena actual a la pantalla del gestor de preguntas.
     * Llama al método 'changeScene' del controlador actual para cambiar la escena a la del gestor de preguntas.
     * @throws IOException Si hay un error al cargar la escena del gestor de preguntas.
     */
    public void changeToQuestionGestor() throws IOException {
        App.currentController.changeScene(Scenes.ADMQUESTIONGESTOR, null);
    }
    /**
     * Cambia la escena actual a la pantalla de información del juego.
     * Llama al método 'changeScene' del controlador actual para cambiar la escena a la de información del juego.
     * @throws IOException Sí hay un error al cargar la escena de información del juego.
     */
    public void changeToGameInformation() throws IOException {
        App.currentController.changeScene(Scenes.GAMEINFORMATION, null);
    }

}
