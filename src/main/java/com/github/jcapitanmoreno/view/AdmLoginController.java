package com.github.jcapitanmoreno.view;

import com.github.jcapitanmoreno.App;
import com.github.jcapitanmoreno.model.dao.AdminDAO;
import com.github.jcapitanmoreno.model.entity.Admin;
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
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void logInAdm() throws Exception {
        if (password != null && user != null) {
            admin.setUser(user.getText());
            admin.setPassword(password.getText());
            boolean loginSuccessful = adminDAO.logIn(admin);

            if (loginSuccessful) {
                changeToAdmGestor();
            } else {
             errorAlert(stage);
            }
        }
    }

    public void changeToAdmGestor() throws IOException {
        App.currentController.changeScene(Scenes.ADMGESTOR, null);
    }
    public void errorAlert(Stage primaryStage) throws Exception {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("707 ERROR");
        alert.setHeaderText("ERROR DE INICIO DE SESSION");
        alert.setContentText("no tienes acceso. Prueba otra vez o contacta con un administrador autorizado baby");

        alert.showAndWait();
    }
}