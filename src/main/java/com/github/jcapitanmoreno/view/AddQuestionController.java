package com.github.jcapitanmoreno.view;

import com.github.jcapitanmoreno.App;
import com.github.jcapitanmoreno.model.dao.AnswerDAO;
import com.github.jcapitanmoreno.model.dao.GameDAO;
import com.github.jcapitanmoreno.model.dao.QuestionsDAO;
import com.github.jcapitanmoreno.model.entity.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddQuestionController extends Controller implements Initializable {
    QuestionsDAO questionsDAO = new QuestionsDAO();
    AnswerDAO answerDAO = new AnswerDAO();
    Question question = new Question();
    Answer answer = new Answer();
    Session session = new Session();


    @FXML
    private TextField questionField;
    @FXML
    private TextField answer1;
    @FXML
    private TextField answer2;
    @FXML
    private TextField answer4;
    @FXML
    private Button add;
    @FXML
    private ImageView infoImage;

    @Override
    public void onOpen(Object input) throws IOException {
    }
    @Override
    public void onClose(Object output) {
    }
    /**
     * Displays an information alert dialog.
     *
     * @param text1 The title of the alert.
     * @param text2 The header text of the alert.
     * @param text3 The content text of the alert.
     */
    @Override
    public void informationAlert(String text1, String text2, String text3) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(text1);
        alert.setHeaderText(text2);
        alert.setContentText(text3);
        alert.show();
    }
    /**
     * Displays an error alert dialog.
     *
     * @param text1 The title of the error alert.
     * @param text2 The header text of the error alert.
     * @param text3 The content text of the error alert.
     */
    @Override
    public void errorAlert(String text1, String text2, String text3) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(text1);
        alert.setHeaderText(text2);
        alert.setContentText(text3);
        alert.show();
    }
    @Override
    public void warningAlert(String text1, String text2, String text3) {
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    /**
     * Adds a new question and its answers to the database.
     * Displays error or information alerts based on success or failure.
     */
    public void addQuestion() {
        GameDAO gameDAO = new GameDAO();
        Game game = gameDAO.findById(1);
        String questionText = questionField.getText();
        if (questionText != null && !questionText.trim().isEmpty()) {
            Player currentPlayer = session.getPlayerLoged();
            if (currentPlayer == null) {
                errorAlert("Error", "Jugador no encontrado", "No se puede encontrar el jugador en la sesión.");
            }
            question.setQuestionText(questionText);
            question.setPlayerInsertQuestion(session.getPlayerLoged());
            question.setGame(game);
            questionsDAO.save(question);

            int questionID = question.getQuestionID();
            saveAnswer(answer1.getText(), true, questionID, currentPlayer);
            saveAnswer(answer2.getText(), false, questionID, currentPlayer);
            saveAnswer(answer4.getText(), false, questionID, currentPlayer);

            informationAlert("Pregunta Añadida", "Éxito", "La pregunta y sus respuestas han sido añadidas correctamente. Disfruta jugando :)");
        } else {
            errorAlert("ERROR 888","Pregunta en blanco","La pregunta no puede estar en blanco.");
        }
        closeWindow();
    }
    /**
     * Saves an answer to the database.
     *
     * @param answerText   The text of the answer.
     * @param isCorrect    Indicates whether the answer is correct or not.
     * @param questionID   The ID of the question to which the answer belongs.
     * @param player       The player who provided the answer.
     */
    private void saveAnswer(String answerText, boolean isCorrect, int questionID, Player player) {
        if (answerText != null && !answerText.trim().isEmpty()) {
            Answer answer = new Answer();
            answer.setQuestion(new Question());
            answer.getQuestion().setQuestionID(questionID);
            answer.setAnswerText(answerText);
            answer.setValidateAnswer(isCorrect);
            answer.setPlayer(player);
            answerDAO.save(answer);
        }
    }
    /**
     * Displays an information alert about the first answer.
     */
    public void infoAlert() {
        informationAlert("INFORMACION SOBRE LA RESPUESTA 1","RESPUESTA Nº1","la respuesta Nº1 siempre sera la correcta, asegurate de que lo introducido sea la respuesta correcta.Cuando juegues las respuestas de ordenaran de manera aleatoria.");
    }
    /**
     * Closes the current window.
     */
    public void closeWindow() {
        Stage stage = (Stage) add.getScene().getWindow();
        stage.close();
    }
}
