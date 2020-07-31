package com.almasb.test.sample;
import com.almasb.test.sample.Controller2;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.DoubleToIntFunction;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.almasb.test.reader.ReadText;

public class Controller {
    @FXML
    private TextField t2;

    @FXML
    private Button b3;

    @FXML
    private Button b2;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField t1;

    @FXML
    private Button b1;

    static ReadText readText;
    @FXML
    void initialize() {
        runTranslation();
        System.out.println("public class Controller ");
        b1.setOnAction(actionEvent -> {
            System.out.println(t1.getCharacters());
            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("/sample/helloWorld.fxml"));
            FileChooser filedir = new FileChooser();
            final String dir = System.getProperty("user.dir");
            filedir.setInitialDirectory(new File(dir));
            File TextFile = filedir.showOpenDialog(new Stage());
            if (TextFile == null)
                return;
            t1.setText(TextFile.getAbsolutePath());
            t2.setText(dir);

            readText = new ReadText(new String[]{TextFile.getAbsolutePath(), dir});

        });
            b3.setOnAction(actionEvent -> {
                AppSettings.setParesedFilePath(t2.getText()+"\\out.txt");
                URL fxmlURL = getClass().getResource("table.fxml");
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(fxmlURL);
                Parent root;
                try {
                    root = loader.load();
                  } catch (IOException e) {
                    root = null;
                System.out.println("table.fxml load error");
                      e.printStackTrace();
            }


            Stage stage = new Stage();
            Scene scene = new Scene(root);
                stage.widthProperty().addListener((obs, oldVal, newVal) -> {
                    // Do whatever you want

                });

            stage.setScene(scene);
            stage.showAndWait();
        });


    }
    public  void  runTranslation(){
        {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlURL = getClass().getResource("TextTranslate.fxml");
            System.out.println();
            loader.setLocation(fxmlURL);
//            loader.setLocation(getClass().getResource("TextTranslate.fxml"));
            Parent root;
            try {
                FXMLLoader.load(fxmlURL);
                root = loader.load(fxmlURL);
                System.out.println();

            } catch (IOException e) {
                root=null;
                System.out.println("table.fxml load error");
                e.printStackTrace();
            }
//            Parent root = loader.getRoot();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
        }
    }

}
