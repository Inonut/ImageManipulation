package app.gui.controller.impl;

import app.gui.model.AppModelView;
import app.util.Command;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Dragos on 18.06.2016.
 */
public class AppController implements Initializable {
    public Slider redSlider;
    public Pane canvasPane;
    public Canvas canvas;
    public GridPane grid;
    public Label redSliderLabel;
    public ImageView inportedImage;
    public Slider greenSlider;
    public Label greenSliderLabel;
    public Slider blueSlider;
    public Label blueSliderLabel;
    public Slider opacitySlider;
    public Label opacitySliderLabel;
    public Slider contrastSlider;
    public Label contrastSliderLabel;
    public Slider hueSlider;
    public Label hueSliderLabel;
    public Slider brightnessSlider;
    public Label brightnessSliderLabel;
    public Slider saturationSlider;
    public Label saturationSliderLabel;

    private AppControllerScala appControllerScala;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        appControllerScala = new AppControllerScala(new AppModelView(
                Command.VIEW,
                canvas,
                canvasPane,
                grid,
                redSlider,
                redSliderLabel,
                greenSlider,
                greenSliderLabel,
                blueSlider,
                blueSliderLabel,
                opacitySlider,
                opacitySliderLabel,
                contrastSlider,
                contrastSliderLabel,
                hueSlider,
                hueSliderLabel,
                brightnessSlider,
                brightnessSliderLabel,
                saturationSlider,
                saturationSliderLabel,
                inportedImage
        ));

        StringConverter<Number> converter = new NumberStringConverter();
        Bindings.bindBidirectional(redSliderLabel.textProperty(), redSlider.valueProperty(), converter);
        Bindings.bindBidirectional(greenSliderLabel.textProperty(), greenSlider.valueProperty(), converter);
        Bindings.bindBidirectional(blueSliderLabel.textProperty(), blueSlider.valueProperty(), converter);
        Bindings.bindBidirectional(opacitySliderLabel.textProperty(), opacitySlider.valueProperty(), converter);

        Bindings.bindBidirectional(contrastSliderLabel.textProperty(), contrastSlider.valueProperty(), converter);
        Bindings.bindBidirectional(hueSliderLabel.textProperty(), hueSlider.valueProperty(), converter);
        Bindings.bindBidirectional(brightnessSliderLabel.textProperty(), brightnessSlider.valueProperty(), converter);
        Bindings.bindBidirectional(saturationSliderLabel.textProperty(), saturationSlider.valueProperty(), converter);

    }

    public void onImport_Click(ActionEvent actionEvent) {
        appControllerScala.onImport_Click(actionEvent);
    }

    public void onClear_Click(ActionEvent actionEvent) {
        appControllerScala.onClear_Click(actionEvent);
    }

    public void onReset_Click(ActionEvent actionEvent) {
        appControllerScala.onReset_Click(actionEvent);
    }
}
