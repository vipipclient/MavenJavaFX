package com.almasb.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.net.URI;
import java.net.URL;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class FXApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
         stage.setTitle("ILYAAAAAAAAAAAAAAAAAAAAAAAAAAAAAASSSSSS");
        URL fxmlURL = getClass().getResource("sample.fxml");
        Parent root = FXMLLoader.load(fxmlURL);


//        stage.setScene(new Scene(new Pane(), 100, 100));
        stage.setScene(new Scene(root, 100, 100));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
