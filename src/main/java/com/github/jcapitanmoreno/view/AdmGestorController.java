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
     * Changes the scene to the question management view.
     *
     * @throws IOException if an error occurs during scene change.
     */
    public void changeToQuestionGestor() throws IOException {
        App.currentController.changeScene(Scenes.ADMQUESTIONGESTOR, null);
    }
    /**
     * Changes the scene to the game information view.
     *
     * @throws IOException if an error occurs during scene change.
     */
    public void changeToGameInformation() throws IOException {
        App.currentController.changeScene(Scenes.GAMEINFORMATION, null);
    }
}
