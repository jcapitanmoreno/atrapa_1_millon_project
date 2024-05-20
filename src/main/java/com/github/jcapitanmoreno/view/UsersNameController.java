package com.github.jcapitanmoreno.view;

import com.github.jcapitanmoreno.App;
import com.github.jcapitanmoreno.model.dao.PlayerDAO;
import com.github.jcapitanmoreno.model.entity.Player;
import com.github.jcapitanmoreno.model.entity.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UsersNameController extends Controller implements Initializable {

    Session session = new Session();
    Player player = new Player();
    PlayerDAO playerDAO = new PlayerDAO();


    @FXML
    private TextField name;
    @FXML
    private Button entrar;

    private Circle secretAdminEnter;

    /**
     * Changes the scene to the administrator login scene.
     *
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    public void changeToADM() throws IOException {
        App.currentController.changeScene(Scenes.ADMLOGIN, null);
    }

    @Override
    public void onOpen(Object input) throws IOException {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    /**
     * Starts a new session for the player.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void startSession() throws IOException {
        player.setName(name.getText());
        if (player.getName() !=null){
            playerDAO.save(player);
            session.login(player);
            App.currentController.changeScene(Scenes.USERMENU, null);
        }
    }

}
