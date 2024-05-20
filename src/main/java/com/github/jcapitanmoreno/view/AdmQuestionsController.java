package com.github.jcapitanmoreno.view;

import com.github.jcapitanmoreno.App;
import com.github.jcapitanmoreno.model.dao.QuestionsDAO;
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
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AdmQuestionsController  extends Controller implements Initializable {

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
    @FXML
    private Button delete;

    /**
     * Initializes the window when opened, adjusting display settings and populating the TableView with questions.
     *
     * @param input An optional input object (not used in this method).
     * @throws IOException If an I/O error occurs.
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
     * Displays an error alert with the specified title, header, and content text.
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
     * Initializes the controller class. This method is automatically called after
     * the FXML file has been loaded. It sets up the TableView to be editable and
     * configures the columns for displaying and editing questions.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
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
                QuestionsDAO.build().update(question);
            } else {
                errorAlert("101 ERROR","ERROR AL AÑADIR UNA PREGUNTA","Pregunta demasiado grande, tiene que tener menos caracteres.");
            }
        });
    }
    /**
     * Opens a modal window for adding a new question and then reloads the list of questions.
     * This method is triggered when the user wants to add a new question.
     *
     * @throws IOException if an input or output exception occurs.
     */
    @FXML
    private void addQuestion() throws IOException {
        App.currentController.openModal(Scenes.ADDQUESTION, "Añada aqui su pregunta y respuestas.", this, null);
        reloadQuestions();
    }
    /**
     * Reloads the list of questions from the database and updates the TableView.
     * This method is used to refresh the displayed list of questions.
     */
    private void reloadQuestions() {
        List<Question> questionList = QuestionsDAO.build().findAll();
        this.questions.setAll(questionList);
        tableView.refresh();
    }
    /**
     * Changes the current scene to the user menu.
     * This method is used to navigate back to the user menu.
     *
     * @throws IOException if there is an error while changing the scene.
     */
    public void backToUserMenu() throws IOException {
        App.currentController.changeScene(Scenes.ADMGESTOR, null);
    }
    /**
     * Augments the display by adjusting the stage dimensions.
     * This method adjusts the width and height of the stage to 800x600.
     */
    public void augmentedDisplay() {
        Stage stage = (Stage) tableView.getScene().getWindow();
        stage.setWidth(800);
        stage.setHeight(600);
    }
    /**
     * Deletes the selected row from the table view.
     * This method removes the selected question from the table view and deletes it from the database.
     * @throws SQLException if there is an error deleting the question from the database.
     */
    @FXML
    private void deleteRow() throws SQLException {
        QuestionsDAO questionsDAO = new QuestionsDAO();
        Question questtodelete = tableView.getSelectionModel().getSelectedItem();
        tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItem());
        questionsDAO.delete(questtodelete);
    }

}
