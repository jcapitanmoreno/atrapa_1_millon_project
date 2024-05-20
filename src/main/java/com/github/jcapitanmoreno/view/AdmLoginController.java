package com.github.jcapitanmoreno.view;

import com.github.jcapitanmoreno.App;
import com.github.jcapitanmoreno.model.dao.AdminDAO;
import com.github.jcapitanmoreno.model.entity.Admin;
import com.github.jcapitanmoreno.model.entity.Player;
import com.github.jcapitanmoreno.model.entity.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdmLoginController extends Controller implements Initializable {

    Admin admin = new Admin();
    AdminDAO adminDAO = new AdminDAO();
    @FXML
    private TextField user;
    @FXML
    private PasswordField password;
    @FXML
    private Button logIn;

    public static Stage stage;


    @Override
    public void onOpen(Object input) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void informationAlert(String text1, String text2, String text3) {

    }
    /**
     * Displays an error alert dialog with the specified title, header, and content text.
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
        alert.showAndWait();
    }

    @Override
    public void warningAlert(String text1, String text2, String text3) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    /**
     * Attempts to log in an admin user.
     *
     * @throws Exception if there is an error during the login process.
     */
    public void logInAdm() throws Exception {
        if (password != null && user != null) {
            admin.setUser(user.getText());
            admin.setPassword(password.getText());
            boolean loginSuccessful = adminDAO.logIn(admin);

            if (loginSuccessful) {
                Player player = new Player(1,"adm",0);
                Session session = new Session();
                session.login(player);
                changeToAdmGestor();
            } else {
             errorAlert(stage);
            }
        }
    }
    /**
     * Cambia la escena actual a la del gestor de administrador.
     * Llama al método 'changeScene' del controlador actual para cambiar la escena a la del gestor de administrador.
     * @throws IOException Si hay un error al cargar la escena del gestor de administrador.
     */
    public void changeToAdmGestor() throws IOException {
        App.currentController.changeScene(Scenes.ADMGESTOR, null);
    }
    /**
     * Displays an error alert when there is an issue with logging in.
     *
     * @param primaryStage The primary stage of the application, though it is not used directly in this method.
     * @throws Exception if there is an error during the alert display process.
     */
    public void errorAlert(Stage primaryStage) throws Exception {
        errorAlert("707 ERROR","ERROR DE INICIO DE SESSION","no tienes acceso. Prueba otra vez o contacta con un administrador autorizado baby");
    }
}