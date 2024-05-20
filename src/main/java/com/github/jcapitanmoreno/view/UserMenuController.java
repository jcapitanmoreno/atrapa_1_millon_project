package com.github.jcapitanmoreno.view;

import com.github.jcapitanmoreno.App;
import com.github.jcapitanmoreno.model.dao.PlayerDAO;
import com.github.jcapitanmoreno.model.dao.QuestionsDAO;
import com.github.jcapitanmoreno.model.entity.Player;
import com.github.jcapitanmoreno.model.entity.Question;
import com.github.jcapitanmoreno.model.entity.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
    public void informationAlert(String text1, String text2, String text3) {

    }

    @Override
    public void errorAlert(String text1, String text2, String text3) {

    }
    /**
     * Displays a warning alert with the given title, header text, and content text.
     *
     * @param text1 The title of the alert.
     * @param text2 The header text of the alert.
     * @param text3 The content text of the alert.
     */
    @Override
    public void warningAlert(String text1, String text2, String text3) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(text1);
        alert.setHeaderText(text2);
        alert.setContentText(text3);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    /**
     * Changes the scene to the user gestor scene.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void changeEsceneToGestorUser() throws IOException {
        App.currentController.changeScene(Scenes.USERGESTOR, null);

    }
    /**
     * Cambia la escena actual a la escena de información del juego.
     * Verifica si hay suficientes preguntas para jugar. Si hay al menos 10 preguntas, se restablecen los puntos del jugador
     * actual y se cambia la escena. Si no hay suficientes preguntas, se muestra una alerta de advertencia.
     *
     * @throws IOException Si hay un error al cambiar a la escena de información del juego.
     */
    public void changeEsceneToGameInformation() throws IOException {
        QuestionsDAO questionDAO = new QuestionsDAO();
        int questionCount = questionDAO.countQuestions();

        if (questionCount >= 10) {
            Player currentPlayer = Session.get_Instance().getPlayerLoged();
            currentPlayer.setEarnedPoints(0);

            PlayerDAO playerDAO = new PlayerDAO();
            playerDAO.save(currentPlayer);

            App.currentController.changeScene(Scenes.GAMEINFORMATION, null);
        } else {
            warningAlert("Advertencia","No hay suficientes preguntas","Debe haber al menos 10 preguntas añadidas para poder jugar.");
        }
    }
    /**
     * Changes the scene to the game information scene if there are at least 10 questions added.
     * Otherwise, displays a warning alert indicating the requirement.
     *
     * @throws IOException If an I/O error occurs.
     */

}
