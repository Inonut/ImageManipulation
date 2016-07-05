package app2;

import app2.util.Constants;
import app2.gui.view.FXML;
import app2.gui.view.css.CSS;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Created by Dragos on 7/5/2016.
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
