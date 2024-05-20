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

    /**
     * Executes tasks when the scene is opened.
     * Augments the display by resizing the stage.
     * Retrieves a list of questions from the database and populates the table view with them.
     * Assumes the database access is managed by the QuestionsDAO class.
     * Assumes the table view is managed by the current controller instance.
     * Throws an IOException if an error occurs during the database query or table view population.
     */
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
    public void informationAlert(String text1, String text2, String text3) {

    }
    /**
     * Displays an error alert dialog box with the provided title, header, and content text.
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
    /**
     * Initializes the controller after its root element has been completely processed.
     * Configures the table view columns and sets up cell editing functionality for the question column.
     * If the new value of the edited question is longer than 230 characters, displays an error alert.
     *
     * @param url            The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tableView.setEditable(false);
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
                errorAlert("101 ERROR","ERROR AL AÑADIR UNA PREGUNTA","Pregunta demasiado grande, tiene que tener menos caracteres.");
            }
        });
    }
    /**
     * Opens a modal dialog to add a new question.
     * The modal dialog is opened using the ADDQUESTION scene.
     * After adding the question, reloads the questions displayed in the table view.
     *
     * @throws IOException If an I/O error occurs while opening the modal dialog.
     */
    @FXML
    private void addQuestion() throws IOException {
        App.currentController.openModal(Scenes.ADDQUESTION, "Añada aqui su pregunta y respuestas.", this, null);
        reloadQuestions();
    }
    /**
     * Reloads the questions displayed in the table view.
     * Retrieves the list of questions from the database using QuestionsDAO,
     * sets the observable list of questions, and refreshes the table view.
     */
    private void reloadQuestions() {
        List<Question> questionList = QuestionsDAO.build().findAll();
        this.questions.setAll(questionList);
        tableView.refresh();
    }
    /**
     * Changes the scene to the user menu.
     * Uses the App.currentController to change the scene to the user menu scene.
     * Throws an IOException if there is an error during the scene change.
     */
    public void backToUserMenu() throws IOException {
        App.currentController.changeScene(Scenes.USERMENU, null);
    }
    /**
     * Adjusts the display of the stage to the specified dimensions.
     * Retrieves the stage from the tableView's scene and sets its width to 800 and height to 600.
     */
    public void augmentedDisplay() {
        Stage stage = (Stage) tableView.getScene().getWindow();
        stage.setWidth(800);
        stage.setHeight(600);
    }


}
