package app2.gui.model

import java.io.File
import javafx.scene.image.Image
import javafx.stage.Window

/**
  * Created by Dragos on 7/5/2016.
  */
case class AppModel(
                   window: Window = null,
                   file: File = null,
                   image: Image = null,
                   imageWidth: Double = 0,
                   imageHeight: Double = 0,
                   canvasImage: Image = null,
                   canvasWidth: Double = 0,
                   canvasHeight: Double = 0,

                   percentRed: Double = 0,
                   percentGreen: Double = 0,
                   percentBlue: Double = 0,
                   opacity: Double = 0,

                   contrast: Double = 0,
                   hue: Double = 0,
                   brightness: Double = 0,
                   saturation: Double = 0
                   ) extends Model
