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
     * Muestra una alerta de error al usuario con un título, un encabezado y un contenido de texto especificados.
     * Este método bloquea la ejecución hasta que el usuario cierra la alerta.
     *
     * @param text1 El título de la alerta.
     * @param text2 El texto del encabezado de la alerta.
     * @param text3 El contenido del texto de la alerta.
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
     * Realiza el inicio de sesión para un administrador.
     * Si el nombre de usuario y la contraseña no son nulos, intenta iniciar sesión.
     * Si el inicio de sesión es exitoso, cambia a la pantalla de gestor de administrador.
     * Si el inicio de sesión falla, muestra una alerta de error.
     * @throws Exception Si hay un error durante el inicio de sesión.
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
     * Muestra una alerta de error al usuario con un título predeterminado y un mensaje de error específico.
     * Este método sobrecargado asume que el escenario principal (primaryStage) ya está disponible y se utiliza para mostrar la alerta.
     * @param primaryStage El escenario principal de la aplicación donde se mostrará la alerta de error.
     * @throws Exception Si hay un error al mostrar la alerta de error.
     */
    public void errorAlert(Stage primaryStage) throws Exception {
        errorAlert("707 ERROR","ERROR DE INICIO DE SESSION","no tienes acceso. Prueba otra vez o contacta con un administrador autorizado baby");
    }
}