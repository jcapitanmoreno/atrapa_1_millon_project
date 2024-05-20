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
     * Opens the scene and loads the list of players from the database.
     *
     * @param input The input object.
     * @throws IOException If an I/O exception occurs.
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
     * Initializes the TableView columns with their respective cell value factories.
     *
     * @param url            The location used to resolve relative paths for the root object.
     * @param resourceBundle The resources used to localize the root object, or null if none.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnID.setCellValueFactory(player -> new SimpleIntegerProperty(player.getValue().getPlayerID()).asObject());
        columnPlayer.setCellValueFactory(player -> new SimpleStringProperty(player.getValue().getName()));
        columnPoints.setCellValueFactory(player -> new SimpleIntegerProperty(player.getValue().getEarnedPoints()).asObject());

    }
    /**
     * Adjusts the size of the stage to 700x480 pixels.
     * Assumes the TableView is placed within a Scene, and the stage is obtained from the TableView's scene.
     */
    public void augmentedDisplay() {
        Stage stage = (Stage) tableView.getScene().getWindow();
        stage.setWidth(700);
        stage.setHeight(480);
    }
    /**
     * Changes the scene to the user name input scene.
     * Assumes the scene transition is managed by the current controller instance.
     * Throws an IOException if the scene change fails.
     */
    public void changeSceneToUserName() throws IOException {
        App.currentController.changeScene(Scenes.USERSNAME, null);
    }
}
