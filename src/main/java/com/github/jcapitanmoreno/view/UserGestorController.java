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
     * Se llama cuando se abre la vista.
     * Realiza la visualización aumentada.
     * Carga la lista de preguntas desde la base de datos y la muestra en la tabla.
     *
     * @param input El objeto de entrada, no se utiliza en esta implementación.
     * @throws IOException Si hay un error al realizar alguna acción relacionada con la visualización.
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
     * Muestra una alerta de error con los textos especificados.
     *
     * @param text1 El título de la alerta de error.
     * @param text2 El encabezado de la alerta de error.
     * @param text3 El contenido de la alerta de error.
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
     * Inicializa la vista cuando se carga.
     * Configura la tabla para mostrar las preguntas y permite la edición del texto de las preguntas.
     * También maneja los eventos de edición de las celdas de pregunta.
     *
     * @param url La ubicación relativa del archivo FXML.
     * @param resourceBundle Los recursos localizados.
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
     * Abre una ventana modal para agregar una nueva pregunta y respuestas.
     * Después de agregar la pregunta, recarga la lista de preguntas en la vista.
     *
     * @throws IOException Si hay un error al abrir la ventana modal o al recargar las preguntas.
     */
    @FXML
    private void addQuestion() throws IOException {
        App.currentController.openModal(Scenes.ADDQUESTION, "Añada aqui su pregunta y respuestas.", this, null);
        reloadQuestions();
    }
    /**
     * Recarga la lista de preguntas en la tabla de la vista.
     * Esto implica recuperar todas las preguntas de la base de datos, actualizar la lista observable de preguntas
     * y refrescar la vista de la tabla para mostrar los cambios.
     */
    private void reloadQuestions() {
        List<Question> questionList = QuestionsDAO.build().findAll();
        this.questions.setAll(questionList);
        tableView.refresh();
    }
    /**
     * Cambia la escena actual a la escena del menú de usuario.
     *
     * @throws IOException Si hay un error al cambiar a la escena del menú de usuario.
     */
    public void backToUserMenu() throws IOException {
        App.currentController.changeScene(Scenes.USERMENU, null);
    }
    /**
     * Realiza una visualización aumentada ajustando el tamaño de la ventana principal.
     * Este método cambia el ancho y la altura de la ventana principal.
     */
    public void augmentedDisplay() {
        Stage stage = (Stage) tableView.getScene().getWindow();
        stage.setWidth(800);
        stage.setHeight(600);
    }


}
