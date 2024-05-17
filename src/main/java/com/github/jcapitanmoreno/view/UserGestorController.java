package com.github.jcapitanmoreno.view;

import com.github.jcapitanmoreno.App;
import com.github.jcapitanmoreno.model.dao.QuestionsDAO;
import com.github.jcapitanmoreno.model.entity.Player;
import com.github.jcapitanmoreno.model.entity.Question;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserGestorController extends Controller implements Initializable {

    @FXML
    private TableView<Question> tableView;

    @FXML
    private TableColumn<Question, Integer> columnID;
    @FXML
    private TableColumn<Question, String> columnQuestion;
    private ObservableList<Question> questions;
    @FXML
    private Button add;
    @FXML
    private Button back;


    @Override
    public void onOpen(Object input) throws IOException {
        augmentedDisplay();
        List<Question> questionList = QuestionsDAO.build().findAll();
        this.questions = FXCollections.observableArrayList(questionList);
        tableView.setItems(this.questions);

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tableView.setEditable(true);
        columnID.setCellValueFactory(question -> new SimpleIntegerProperty(question.getValue().getQuestionID()).asObject());
        columnQuestion.setCellValueFactory(question -> new SimpleStringProperty(question.getValue().getQuestionText()));
        columnQuestion.setCellFactory(TextFieldTableCell.forTableColumn());
        columnQuestion.setOnEditCommit(event -> {
            if (event.getNewValue() == event.getOldValue()) {
                return;
            }
            if (event.getNewValue().length() <= 230) {
                Question question = event.getRowValue();
                question.setQuestionText(event.getNewValue());
                QuestionsDAO.build().save(question);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("101 ERROR");
                alert.setHeaderText("ERROR AL AÑADIR UNA PREGUNTA");
                alert.setContentText("Pregunta demasiado grande, tiene que tener menos caracteres.");
                alert.show();
            }
        });
    }

    @FXML
    private void addQuestion() throws IOException {
        App.currentController.openModal(Scenes.ADDQUESTION, "Añada aqui su pregunta y respuestas.", this, null);
    }

    public void backToUserMenu() throws IOException {
        App.currentController.changeScene(Scenes.USERMENU, null);
    }

    public void augmentedDisplay() {
        Stage stage = (Stage) tableView.getScene().getWindow();
        stage.setWidth(800);
        stage.setHeight(600);
    }


}
