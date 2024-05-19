package com.github.jcapitanmoreno.view;

import com.github.jcapitanmoreno.App;
import com.github.jcapitanmoreno.model.dao.AnswerDAO;
import com.github.jcapitanmoreno.model.dao.PlayerDAO;
import com.github.jcapitanmoreno.model.dao.QuestionsDAO;
import com.github.jcapitanmoreno.model.entity.Answer;
import com.github.jcapitanmoreno.model.entity.Player;
import com.github.jcapitanmoreno.model.entity.Question;
import com.github.jcapitanmoreno.model.entity.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class GameController extends Controller implements Initializable {

    @FXML
    private Label questionLabel;
    @FXML
    private Button answerButton1;
    @FXML
    private Button answerButton2;
    @FXML
    private Button answerButton3;
    @FXML
    private Label pointsLabel;

    private List<Question> questions;
    private List<Answer> answers;
    int currentQuestionIndex = 0;

    @Override
    public void onOpen(Object input) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadQuestions();
        if (questions != null && !questions.isEmpty()) {
            try {
                displayQuestion();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            showNoQuestionsAlert();
        }
        updatePoints();
    }

    public void loadQuestions() {
        QuestionsDAO questionDAO = new QuestionsDAO();
        questions = questionDAO.findAll();
        if (questions != null && !questions.isEmpty()) {
            Collections.shuffle(questions);
            if (questions.size() > 10) {
                questions = questions.subList(0, 10);
            }
        }
    }
    public void loadAnswers(int key){
        AnswerDAO answerDAO = new AnswerDAO();
        answers = answerDAO.findByQuestionID(key);
        if (answers != null && !answers.isEmpty()) {
            Collections.shuffle(answers);
            if (answers.size() > 3) {
                answers = answers.subList(0, 2);
            }
        }

    }

    public void displayQuestion() throws IOException {
        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            loadAnswers(currentQuestion.getQuestionID());
            questionLabel.setText(currentQuestion.getQuestionText());
            if (answers != null && answers.size() >= 3) {

                answerButton1.setText(answers.get(0).getAnswerText());
                answerButton2.setText(answers.get(1).getAnswerText());
                answerButton3.setText(answers.get(2).getAnswerText());

            } else {
                showNoAnswersAlert();
            }
        } else {
            endGame();
        }
    }

    public void checkAnswer1() throws IOException {
        Answer answer = answers.get(0);
        checkAnswer(answer);
    }

    public void checkAnswer2() throws IOException {
        Answer answer = answers.get(1);
        checkAnswer(answer);
    }

    public void checkAnswer3() throws IOException {
        Answer answer = answers.get(2);
        checkAnswer(answer);
    }
    private void checkAnswer(Answer answer) throws IOException {
        if (answer.isValidateAnswer()) {
            Player currentPlayer = Session.get_Instance().getPlayerLoged();
            currentPlayer.setEarnedPoints(currentPlayer.getEarnedPoints() + 100000);
            PlayerDAO playerDAO = new PlayerDAO();
            playerDAO.save(currentPlayer);
            currentQuestionIndex++;
            displayQuestion();
            updatePoints();
        }else {
            endGame();

        }

    }


    public void updatePoints() {
        Player currentPlayer = Session.get_Instance().getPlayerLoged();
        pointsLabel.setText("Euros: " + currentPlayer.getEarnedPoints());

    }

    public void endGame() throws IOException {
        Player currentPlayer = Session.get_Instance().getPlayerLoged();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Juego terminado");
        alert.setHeaderText(null);
        alert.setContentText("¡Juego terminado! Has ganado " + currentPlayer.getEarnedPoints() + " Euros.");
        alert.showAndWait();
        changeToPoints();
    }

    public void showNoQuestionsAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("No hay preguntas disponibles");
        alert.setContentText("No hay suficientes preguntas en la base de datos para iniciar el juego.");
        alert.showAndWait();
    }

    public void showNoAnswersAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("No hay respuestas disponibles");
        alert.setContentText("No hay suficientes respuestas para la pregunta actual.");
        alert.showAndWait();
    }
    public void changeToPoints() throws IOException {
        App.currentController.changeScene(Scenes.PLAYERPOINTS, null);
    }
}

