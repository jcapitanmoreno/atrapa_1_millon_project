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
     * Método invocado al abrir la ventana.
     * Realiza la configuración inicial de la ventana, muestra una visualización aumentada,
     * carga todas las preguntas disponibles desde la base de datos y las muestra en una tabla.
     * @param input El objeto de entrada, si es necesario para el método (puede ser null en este caso).
     * @throws IOException Si ocurre un error durante la carga de datos o la visualización.
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
     * Muestra una alerta de error al usuario con un título, un encabezado y un contenido de texto especificados.
     * Este método muestra la alerta de error sin bloquear la ejecución del programa.
     *
     * @param text1 El título de la alerta de error.
     * @param text2 El texto del encabezado de la alerta de error.
     * @param text3 El contenido del texto de la alerta de error.
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
     * Inicializa la interfaz de usuario cuando se carga el controlador.
     * Configura la tabla para ser editable y define las celdas de la tabla para mostrar y editar preguntas.
     *
     * @param url La ubicación del archivo FXML; no se utiliza en esta implementación.
     * @param resourceBundle Los recursos localizados; no se utiliza en esta implementación.
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
     * Método llamado cuando se hace clic en el botón "addQuestion" en la interfaz de usuario.
     * Abre un modal para agregar una nueva pregunta y sus respuestas.
     * Después de agregar la pregunta, recarga la lista de preguntas en la tabla.
     * @throws IOException Si hay un error al cargar el modal de agregar pregunta.
     */
    @FXML
    private void addQuestion() throws IOException {
        App.currentController.openModal(Scenes.ADDQUESTION, "Añada aqui su pregunta y respuestas.", this, null);
        reloadQuestions();
    }
    /**
     * Recarga la lista de preguntas en la tabla.
     * Obtiene todas las preguntas disponibles desde la base de datos, actualiza la lista observable de preguntas
     * y refresca la vista de la tabla para mostrar los cambios.
     */
    private void reloadQuestions() {
        List<Question> questionList = QuestionsDAO.build().findAll();
        this.questions.setAll(questionList);
        tableView.refresh();
    }

    public void backToUserMenu() throws IOException {
        App.currentController.changeScene(Scenes.ADMGESTOR, null);
    }
    /**
     * Cambia la escena actual a la del menú de usuario.
     * Llama al método 'changeScene' del controlador actual para cambiar la escena a la del menú de usuario.
     * @throws IOException Sí hay un error al cargar la escena del menú de usuario.
     */
    public void augmentedDisplay() {
        Stage stage = (Stage) tableView.getScene().getWindow();
        stage.setWidth(800);
        stage.setHeight(600);
    }
    /**
     * Método llamado cuando se hace clic en el botón para eliminar una fila en la tabla.
     * Elimina la pregunta seleccionada de la base de datos y de la vista de la tabla.
     * @throws SQLException Sí hay un error al interactuar con la base de datos.
     */
    @FXML
    private void deleteRow() throws SQLException {
        QuestionsDAO questionsDAO = new QuestionsDAO();
        Question questtodelete = tableView.getSelectionModel().getSelectedItem();
        tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItem());
        questionsDAO.delete(questtodelete);
    }

}
