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
    /**
     * Muestra una alerta de información al usuario con un título, un encabezado y un contenido de texto especificados.
     * Este método bloquea la ejecución hasta que el usuario cierra la alerta.
     *
     * @param text1 El título de la alerta.
     * @param text2 El texto del encabezado de la alerta.
     * @param text3 El contenido del texto de la alerta.
     */
    @Override
    public void informationAlert(String text1, String text2, String text3) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(text1);
        alert.setHeaderText(text2);
        alert.setContentText(text3);
        alert.showAndWait();
    }
    /**
     * Muestra una alerta de error al usuario con un título, un encabezado y un contenido de texto especificados.
     * Este método bloquea la ejecución hasta que el usuario cierra la alerta.
     *
     * @param text1 El título de la alerta.
     * @param text2 El texto del encabezado de la alerta.
     * @param text3 El contenido del texto de la alerta.
     */
    @Override
    public void errorAlert(String text1, String text2, String text3) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(text1);
        alert.setHeaderText(text2);
        alert.setContentText(text3);
        alert.showAndWait();
    }

    @Override
    public void warningAlert(String text1, String text2, String text3) {

    }
    /**
     * Inicializa el controlador cuando se carga la ventana.
     * Carga las preguntas, muestra la primera pregunta si existe, o muestra una alerta si no hay preguntas disponibles.
     * Actualiza los puntos del usuario.
     *
     * @param url La ubicación del archivo FXML; no se utiliza en esta implementación.
     * @param resourceBundle Los recursos localizados; no se utiliza en esta implementación.
     */
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
    /**
     * Carga las preguntas desde la base de datos.
     * Se asegura de que las preguntas se mezclen y, si hay más de 10 preguntas disponibles,
     * selecciona aleatoriamente 10 preguntas de la lista.
     */
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
    /**
     * Carga las respuestas correspondientes a una pregunta específica desde la base de datos.
     * Se asegura de que las respuestas se mezclen y, si hay más de 3 respuestas disponibles,
     * selecciona aleatoriamente 3 respuestas de la lista.
     *
     * @param key El identificador único de la pregunta para la cual se cargan las respuestas.
     */
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
    /**
     * Muestra la pregunta actual y sus respuestas correspondientes en la interfaz de usuario.
     * Si hay más preguntas disponibles, muestra la siguiente pregunta en la lista.
     * Si no hay más preguntas disponibles, finaliza el juego.
     *
     * @throws IOException Si hay un error al cargar la vista.
     */
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
    /**
     * Método llamado cuando se hace clic en el botón de respuesta 1.
     * Verifica si la respuesta seleccionada es correcta y realiza las acciones correspondientes.
     *
     * @throws IOException Si hay un error al realizar alguna acción relacionada con la respuesta.
     */
    public void checkAnswer1() throws IOException {
        Answer answer = answers.get(0);
        checkAnswer(answer);
    }
    /**
     * Método llamado cuando se hace clic en el botón de respuesta 2.
     * Verifica si la respuesta seleccionada es correcta y realiza las acciones correspondientes.
     *
     * @throws IOException Si hay un error al realizar alguna acción relacionada con la respuesta.
     */
    public void checkAnswer2() throws IOException {
        Answer answer = answers.get(1);
        checkAnswer(answer);
    }
    /**
     * Método llamado cuando se hace clic en el botón de respuesta 3.
     * Verifica si la respuesta seleccionada es correcta y realiza las acciones correspondientes.
     *
     * @throws IOException Si hay un error al realizar alguna acción relacionada con la respuesta.
     */
    public void checkAnswer3() throws IOException {
        Answer answer = answers.get(2);
        checkAnswer(answer);
    }
    /**
     * Verifica si la respuesta seleccionada es correcta y realiza las acciones correspondientes.
     *
     * @param answer La respuesta seleccionada por el jugador.
     * @throws IOException Si hay un error al realizar alguna acción relacionada con la respuesta.
     */
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

    /**
     * Actualiza el marcador de puntos en la interfaz de usuario.
     * Obtiene los puntos actuales del jugador actualmente logueado y los muestra en el marcador de puntos.
     */
    public void updatePoints() {
        Player currentPlayer = Session.get_Instance().getPlayerLoged();
        pointsLabel.setText("Euros: " + currentPlayer.getEarnedPoints());

    }
    /**
     * Finaliza el juego y muestra una alerta informando al jugador sobre su puntuación final.
     * Luego, cambia a la pantalla de puntos.
     *
     * @throws IOException Si hay un error al cambiar a la pantalla de puntos.
     */
    public void endGame() throws IOException {
        Player currentPlayer = Session.get_Instance().getPlayerLoged();
        informationAlert("Juego terminado",null,"¡Juego terminado! Has ganado " + currentPlayer.getEarnedPoints() + " Euros.");
        changeToPoints();
    }
    /**
     * Muestra una alerta de error indicando que no hay preguntas disponibles.
     */
    public void showNoQuestionsAlert() {
        errorAlert("Error","No hay preguntas disponibles","No hay suficientes preguntas en la base de datos para iniciar el juego.");
    }

    /**
     * Muestra una alerta de error indicando que no hay respuestas disponibles para la pregunta actual.
     */
    public void showNoAnswersAlert() {
        errorAlert("Error","No hay respuestas disponibles","No hay suficientes respuestas para la pregunta actual.");
    }

    /**
     * Cambia a la escena de puntos del jugador.
     *
     * @throws IOException Si hay un error al cambiar a la escena de puntos del jugador.
     */
    public void changeToPoints() throws IOException {
        App.currentController.changeScene(Scenes.PLAYERPOINTS, null);
    }
}

