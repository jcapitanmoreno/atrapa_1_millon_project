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
     * Muestra una alerta de información al usuario con un título, un encabezado y un contenido de texto especificados.
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
        alert.show();
    }
    /**
     * Muestra una alerta de error al usuario con un título, un encabezado y un contenido de texto especificados.
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
        alert.show();
    }
    @Override
    public void warningAlert(String text1, String text2, String text3) {
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    /**
     * Añade una nueva pregunta al juego. La pregunta y sus respuestas son guardadas en la base de datos.
     * Muestra una alerta de información si la operación es exitosa o una alerta de error en caso de problemas.
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
     * Guarda una respuesta asociada a una pregunta en la base de datos.
     *
     * @param answerText El texto de la respuesta.
     * @param isCorrect Indica si la respuesta es correcta o no.
     * @param questionID El ID de la pregunta a la que está asociada la respuesta.
     * @param player El jugador que proporciona la respuesta.
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
     * Muestra una alerta de información al usuario con detalles sobre la respuesta 1.
     * La alerta informa que la respuesta número 1 siempre será la correcta y que las respuestas se ordenarán aleatoriamente al jugar.
     */
    public void infoAlert() {
        informationAlert("INFORMACION SOBRE LA RESPUESTA 1","RESPUESTA Nº1","la respuesta Nº1 siempre sera la correcta, asegurate de que lo introducido sea la respuesta correcta.Cuando juegues las respuestas de ordenaran de manera aleatoria.");
    }
    /**
     * Cierra la ventana actual de la aplicación.
     * Obtiene la ventana actual a través del nodo 'add' y la cierra.
     */
    public void closeWindow() {
        Stage stage = (Stage) add.getScene().getWindow();
        stage.close();
    }
}
