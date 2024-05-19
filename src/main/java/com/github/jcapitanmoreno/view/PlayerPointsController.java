package com.github.jcapitanmoreno.view;

import com.github.jcapitanmoreno.App;
import com.github.jcapitanmoreno.model.dao.PlayerDAO;
import com.github.jcapitanmoreno.model.dao.QuestionsDAO;
import com.github.jcapitanmoreno.model.entity.Player;
import com.github.jcapitanmoreno.model.entity.Question;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PlayerPointsController  extends Controller implements Initializable {

    @FXML
    private TableView<Player> tableView;
    @FXML
    private TableColumn<Player, Integer> columnID;
    @FXML
    private TableColumn<Player, String> columnPlayer;
    @FXML
    private TableColumn<Player, Integer> columnPoints;
    private ObservableList<Player> players;
    @FXML
    private Button back;

    /**
     * Se llama cuando se abre la vista.
     * Realiza la visualización aumentada.
     * Carga la lista de jugadores desde la base de datos y la muestra en la tabla.
     *
     * @param input El objeto de entrada, no se utiliza en esta implementación.
     * @throws IOException Si hay un error al realizar alguna acción relacionada con la visualización.
     */
    @Override
    public void onOpen(Object input) throws IOException {
        augmentedDisplay();
        List<Player> playerList = PlayerDAO.build().findAll();
        this.players = FXCollections.observableArrayList(playerList);
        tableView.setItems(this.players);
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void informationAlert(String text1, String text2, String text3) {

    }

    @Override
    public void errorAlert(String text1, String text2, String text3) {

    }

    @Override
    public void warningAlert(String text1, String text2, String text3) {

    }
    /**
     * Inicializa la vista cuando se carga.
     * Configura las celdas de la tabla para mostrar la información de los jugadores.
     *
     * @param url La ubicación relativa del archivo FXML.
     * @param resourceBundle Los recursos localizados.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnID.setCellValueFactory(player -> new SimpleIntegerProperty(player.getValue().getPlayerID()).asObject());
        columnPlayer.setCellValueFactory(player -> new SimpleStringProperty(player.getValue().getName()));
        columnPoints.setCellValueFactory(player -> new SimpleIntegerProperty(player.getValue().getEarnedPoints()).asObject());

    }
    /**
     * Realiza una visualización aumentada ajustando el tamaño de la ventana principal.
     * Este método cambia el ancho y la altura de la ventana principal.
     */
    public void augmentedDisplay() {
        Stage stage = (Stage) tableView.getScene().getWindow();
        stage.setWidth(700);
        stage.setHeight(480);
    }
    /**
     * Cambia la escena actual a la escena de nombre de usuario.
     *
     * @throws IOException Si hay un error al cambiar a la escena de nombre de usuario.
     */
    public void changeSceneToUserName() throws IOException {
        App.currentController.changeScene(Scenes.USERSNAME, null);
    }
}
