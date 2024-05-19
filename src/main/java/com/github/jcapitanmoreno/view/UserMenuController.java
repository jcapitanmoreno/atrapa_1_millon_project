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
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void changeEsceneToGestorUser() throws IOException {
        App.currentController.changeScene(Scenes.USERGESTOR, null);

    }
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
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText("No hay suficientes preguntas");
            alert.setContentText("Debe haber al menos 10 preguntas añadidas para poder jugar.");
            alert.showAndWait();
        }
    }

}
