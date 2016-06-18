package app;

import app.gui.view.FXML;
import app.gui.view.css.CSS;
import app.util.Constants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
  * Created by Dragos on 17.06.2016.
  */
public class Main extends Application {

    public static void main(String ...args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL resource = FXML.class.getResource(Constants.applicationFXML());

        Scene scene = new Scene(FXMLLoader.load(resource));
        scene.getStylesheets().add(CSS.class.getResource(Constants.applicationCSS()).toExternalForm());

        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
