package com.github.jcapitanmoreno.view;

import com.github.jcapitanmoreno.App;
import com.github.jcapitanmoreno.model.dao.AdminDAO;
import com.github.jcapitanmoreno.model.entity.Admin;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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


    @Override
    public void onOpen(Object input) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void logInAdm() throws IOException {
        if (password != null && user != null) {
            admin.setUser(user.getText());
            admin.setPassword(password.getText());
            boolean loginSuccessful = adminDAO.logIn(admin);

            // Verificar si el inicio de sesión fue exitoso
            if (loginSuccessful) {
                changeToAdmGestor();
            } else {
                System.out.println("no entro gilipollas");
                // Si no es exitoso, muestra un mensaje de error al usuario
                // Código para abrir un modal con el mensaje de error aquí
                // Por ejemplo:
                // openErrorModal("Usuario o contraseña incorrectos");
            }
        }
    }

    public void changeToAdmGestor() throws IOException {
        App.currentController.changeScene(Scenes.ADMGESTOR, null);
    }
}