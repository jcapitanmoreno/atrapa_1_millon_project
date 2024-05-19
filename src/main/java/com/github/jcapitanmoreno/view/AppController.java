package com.github.jcapitanmoreno.view;

import com.github.jcapitanmoreno.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppController extends Controller implements Initializable {

    @FXML
    private BorderPane borderPane;
    private Controller centerController;

    /**
     * Método invocado al abrir la ventana.
     * Cambia la escena actual a la pantalla de nombres de usuario.
     * @param input El objeto de entrada, si es necesario para el método (puede ser null en este caso).
     * @throws IOException Si hay un error al cargar la escena de nombres de usuario.
     */
    @Override
    public void onOpen(Object input) throws IOException {
        changeScene(Scenes.USERSNAME, null);
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
     * Abre un modal con la escena especificada.
     *
     * @param scene La escena que se cargará en el modal.
     * @param title El título del modal.
     * @param parent El controlador padre que invoca el modal.
     * @param data Los datos opcionales que se pueden pasar al modal.
     * @throws IOException Si hay un error al cargar la escena del modal.
     */
    public void openModal(Scenes scene, String title,Controller parent, Object data) throws IOException {
        View view = loadFXML(scene);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(App.stage);
        Scene _scene = new Scene(view.scene);
        stage.setScene(_scene);
        view.controller.onOpen(parent);
        stage.showAndWait();
    }
    /**
     * Carga el archivo FXML correspondiente a la escena especificada.
     *
     * @param scenes Enumeración que representa la escena a cargar.
     * @return Una instancia de la clase View que contiene la escena y su controlador.
     * @throws IOException Si hay un error al cargar el archivo FXML.
     */
        public static View loadFXML (Scenes scenes) throws IOException {
            String url = scenes.getURL();
            FXMLLoader loader = new FXMLLoader(App.class.getResource(url));
            Parent p = loader.load();
            Controller c = loader.getController();
            View view = new View();
            view.scene = p;
            view.controller = c;
            return view;
        }
    /**
     * Cambia la escena actual en el BorderPane al archivo FXML correspondiente a la escena especificada.
     * Llama al método 'onOpen' del controlador asociado a la nueva escena, pasando los datos proporcionados.
     *
     * @param scene La escena a la que se cambiará.
     * @param data Los datos opcionales que se pueden pasar al controlador de la nueva escena.
     * @throws IOException Si hay un error al cargar el archivo FXML.
     */
        public void changeScene (Scenes scene, Object data) throws IOException {
            View view = loadFXML(scene);
            borderPane.setCenter(view.scene);
            this.centerController = view.controller;
            this.centerController.onOpen(data);
        }
    }
