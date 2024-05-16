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

    @Override
    public void onOpen(Object input) throws IOException {
        changeScene(Scenes.USERSNAME, null);
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
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
        //podríamos leer que ha devuelto...

    }

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
        public void changeScene (Scenes scene, Object data) throws IOException {
            View view = loadFXML(scene);
            borderPane.setCenter(view.scene);
            this.centerController = view.controller;
            this.centerController.onOpen(data);
        }
    }
