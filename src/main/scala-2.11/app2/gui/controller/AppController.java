package app2.gui.controller;

import app2.gui.modelView.AppMV;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import scala.Function1;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Dragos on 7/5/2016.
 */
public class AppController implements Initializable {

    @FXML public Slider redSlider;
    @FXML public Pane canvasPane;
    @FXML public Canvas canvas;
    @FXML public GridPane grid;
    @FXML public Label redSliderLabel;
    @FXML public ImageView inportedImage;
    @FXML public Slider greenSlider;
    @FXML public Label greenSliderLabel;
    @FXML public Slider blueSlider;
    @FXML public Label blueSliderLabel;
    @FXML public Slider opacitySlider;
    @FXML public Label opacitySliderLabel;
    @FXML public Slider contrastSlider;
    @FXML public Label contrastSliderLabel;
    @FXML public Slider hueSlider;
    @FXML public Label hueSliderLabel;
    @FXML public Slider brightnessSlider;
    @FXML public Label brightnessSliderLabel;
    @FXML public Slider saturationSlider;
    @FXML public Label saturationSliderLabel;
    @FXML public Button inportButton;
    @FXML public Button clearButton;
    @FXML public Button resetButton;

    private AppMV appMV = new AppMV(this);

    @Override
    public void initialize(URL location, ResourceBundle resources) { appMV.binding(); }
}
