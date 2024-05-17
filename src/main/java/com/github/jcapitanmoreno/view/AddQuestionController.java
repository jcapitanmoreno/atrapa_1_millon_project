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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void addQuestion() {
        GameDAO gameDAO = new GameDAO();
        Game game = gameDAO.findById(1);
        String questionText = questionField.getText();
        if (questionText != null && !questionText.trim().isEmpty()) {
            Player currentPlayer = session.getPlayerLoged();
            if (currentPlayer == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Jugador no encontrado");
                alert.setContentText("No se puede encontrar el jugador en la sesión.");
                alert.show();
            }

            question.setQuestionText(questionText);
            question.setPlayerInsertQuestion(session.getPlayerLoged());
            question.setGame(game);
            questionsDAO.save(question);

            int questionID = question.getQuestionID();
            saveAnswer(answer1.getText(), true, questionID, currentPlayer);
            saveAnswer(answer2.getText(), false, questionID, currentPlayer);
            saveAnswer(answer4.getText(), false, questionID, currentPlayer);


            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pregunta Añadida");
            alert.setHeaderText("Éxito");
            alert.setContentText("La pregunta y sus respuestas han sido añadidas correctamente. Disfruta jugando :)");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR 888");
            alert.setHeaderText("Pregunta en blanco");
            alert.setContentText("La pregunta no puede estar en blanco.");
            alert.show();
        }
        closeWindow();
    }
    private void saveAnswer(String answerText, boolean isCorrect,int questionID, Player player) {
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

    public void infoAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFORMACION SOBRE LA RESPUESTA 1");
        alert.setHeaderText("RESPUESTA Nº1");
        alert.setContentText("la respuesta Nº1 siempre sera la correcta, asegurate de que lo introducido sea la respuesta correcta. " +
                "Cuando juegues las respuestas de ordenaran de manera aleatoria.");
        alert.show();
    }
    public void closeWindow(){
        Stage stage = (Stage) add.getScene().getWindow();
        stage.close();
    }
}
