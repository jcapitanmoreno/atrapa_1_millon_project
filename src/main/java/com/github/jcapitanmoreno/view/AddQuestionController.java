package com.github.jcapitanmoreno.view;

import com.github.jcapitanmoreno.App;
import com.github.jcapitanmoreno.model.dao.AnswerDAO;
import com.github.jcapitanmoreno.model.dao.QuestionsDAO;
import com.github.jcapitanmoreno.model.entity.Answer;
import com.github.jcapitanmoreno.model.entity.Question;
import com.github.jcapitanmoreno.model.entity.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.w3c.dom.Node;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.events.MouseEvent;

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
        System.out.println("pepepepepepepep");

        question.setQuestionText(questionField.getText());
        if (question.getQuestionText() != null) {
            questionsDAO.save(question);
            session.getPlayerLoged();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("027 ERROR");
            alert.setHeaderText("PREGUNTA EL BLANCO");
            alert.setContentText("la pregunta no puede estar en blanco");
            alert.show();
        }

        //paso1= recoger la pregunta y añadirla a la base de datos a traves de su DAO
        //paso2= recoger el resultado y obtener el ID asignado a la pregunta en la base de datos
        //paso3= recoger las respuestas y pasarle el ID de la pregunta para añadirlo a la base de datos y vincularlo a la pregunta
    }

    public void infoAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFORMACION SOBRE LA RESPUESTA 1");
        alert.setHeaderText("RESPUESTA Nº1");
        alert.setContentText("la respuesta Nº1 siempre sera la correcta, asegurate de que lo introducido sea la respuesta correcta. " +
                "Cuando juegues las respuestas de ordenaran de manera aleatoria.");
        alert.show();
    }

}
