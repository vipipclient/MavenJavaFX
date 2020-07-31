package com.almasb.test.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.almasb.test.reader.HttpIdReqest;

import java.io.File;
import java.net.URL;
import java.util.HashMap;

interface FunctionTest{
    void func(String x);
}
public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        AppSettings.load(new File("conf.xml"));
        HashMap asd = AppSettings.getSettingsHashMap();
        AppSettings.save(new File("testing.xml"));

        System.out.println("trying to load FXML"  );
        URL testing = getClass().getResource("sample.fxml");
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        System.out.println("root is" + root);
        primaryStage.setTitle("Ilyas Text Translator ");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.setMaxWidth(710);
        primaryStage.setMaxHeight(410);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
