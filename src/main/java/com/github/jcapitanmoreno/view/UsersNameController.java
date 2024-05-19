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
     * Cambia la escena actual a la escena de inicio de sesión de administrador.
     *
     * @throws IOException Si hay un error al cambiar a la escena de inicio de sesión de administrador.
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
     * Inicia una sesión de juego.
     * Guarda el nombre del jugador en la base de datos, inicia sesión con ese jugador y cambia a la escena del menú de usuario.
     *
     * @throws IOException Si hay un error al cambiar a la escena del menú de usuario.
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
