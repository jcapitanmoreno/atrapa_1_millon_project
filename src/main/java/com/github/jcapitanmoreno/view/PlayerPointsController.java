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
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnID.setCellValueFactory(player -> new SimpleIntegerProperty(player.getValue().getPlayerID()).asObject());
        columnPlayer.setCellValueFactory(player -> new SimpleStringProperty(player.getValue().getName()));
        columnPoints.setCellValueFactory(player -> new SimpleIntegerProperty(player.getValue().getEarnedPoints()).asObject());

    }
    public void augmentedDisplay() {
        Stage stage = (Stage) tableView.getScene().getWindow();
        stage.setWidth(700);
        stage.setHeight(480);
    }
    public void changeSceneToUserName() throws IOException {
        App.currentController.changeScene(Scenes.USERSNAME, null);
    }
}
