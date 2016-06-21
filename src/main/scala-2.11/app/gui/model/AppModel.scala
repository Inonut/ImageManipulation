package app.gui.model


import javafx.scene.canvas.Canvas
import javafx.scene.control.{Label, Slider}
import javafx.scene.image.{Image, ImageView}
import javafx.scene.layout.{GridPane, Pane}
import javafx.stage.Window

import app.util.Command

/**
  * Created by Dragos on 18.06.2016.
  */
case class AppModel(
                     command: Command = Command.NONE,
                     canvasImage: Image = null,
                     canvasWidth: Double = 0,
                     canvasHeight: Double = 0,
                     window: Window = null,

                     image: Image = null,
                     imageWidth: Double = 0,
                     imageHeight: Double = 0,

                     percentRed: Double = 0,
                     percentGreen: Double = 0,
                     percentBlue: Double = 0,
                     opacity: Double = 0,

                     contrast: Double = 0,
                     hue: Double = 0,
                     brightness: Double = 0,
                     saturation: Double = 0
                   ) extends Model

case class AppModelView(
                         command: Command,
                         canvas: Canvas,
                         canvasPane: Pane,
                         grid: GridPane,
                         redSlider: Slider,
                         redSliderLabel: Label,
                         greenSlider: Slider,
                         greenSliderLabel: Label,
                         blueSlider: Slider,
                         blueSliderLabel: Label,
                         opacitySlider: Slider,
                         opacitySliderLabel: Label,
                         contrastSlider: Slider,
                         contrastSliderLabel: Label,
                         hueSlider: Slider,
                         hueSliderLabel: Label,
                         brightnessSlider: Slider,
                         brightnessSliderLabel: Label,
                         saturationSlider: Slider,
                         saturationSliderLabel: Label,
                         inportedImage: ImageView
                       ) extends Model